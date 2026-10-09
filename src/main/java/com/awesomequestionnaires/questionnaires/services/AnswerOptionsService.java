package com.awesomequestionnaires.questionnaires.services;

import com.awesomequestionnaires.questionnaires.Questionnaire;
import com.awesomequestionnaires.questionnaires.QuestionnaireRepository;
import com.awesomequestionnaires.questionnaires.database.models.AnswerOption;
import com.awesomequestionnaires.questionnaires.database.models.Question;
import com.awesomequestionnaires.questionnaires.database.models.QuestionType;
import com.awesomequestionnaires.questionnaires.database.repositories.AnswerOptionsRepository;
import com.awesomequestionnaires.questionnaires.database.repositories.QuestionRepository;
import com.awesomequestionnaires.questionnaires.dtos.request.CreateAnswerOptionRequest;
import com.awesomequestionnaires.questionnaires.dtos.response.CreateAnswerOptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class AnswerOptionsService {

    private final static boolean NEW_QUESTIONS_STATUS = true;
    private final QuestionnaireRepository questionnaireRepository;
    private final QuestionRepository questionRepository;
    private final AnswerOptionsRepository answerOptionsRepository;

    public AnswerOptionsService(QuestionnaireRepository questionnaireRepository, QuestionRepository questionRepository, AnswerOptionsRepository answerOptionsRepository) {
        this.questionnaireRepository = questionnaireRepository;
        this.questionRepository = questionRepository;
        this.answerOptionsRepository = answerOptionsRepository;
    }

    public List<CreateAnswerOptionResponse> createAnswerOption(UUID questionnaireId, UUID questionId, List<CreateAnswerOptionRequest> createAnswerOptionData) {
        //1- VERIFICAR SE QUESTIONARIO EXISTE
        this.findOneQuestionnaireById(questionnaireId);

        //2- VERIFICAR SE QUESTAO EXISTE
        Question question = this.findOneQuestionById(questionId);

        this.checkIfQuestionAnswerOptionCanBeAsigned(question.getQuestionType());

        //3- TRANSFORMAR OS DADOS DA REQUEST NAS ENTIDADES DO BANCO
        List<AnswerOption> answerOptionsToCreate = createAnswerOptionData.stream()
                .map(answerOption -> new AnswerOption(
                        answerOption.displayText(),
                        answerOption.displayOrder(),
                        NEW_QUESTIONS_STATUS,
                        question
                ))
                .toList();

        //4- GRAVAR AS ANSWER OPTIONS NO BANCO
        List<AnswerOption> savedAnswerOptions = this.answerOptionsRepository.saveAll(answerOptionsToCreate);

        //5- TRANSFORMAR AS ENTIDADES DO BANCO PARA AS RESPONSE
        return savedAnswerOptions.stream()
                .map(CreateAnswerOptionResponse::fromEntity)
                .toList();
    }

    public Questionnaire findOneQuestionnaireById(UUID questionnaireId) {
        return questionnaireRepository.findById(questionnaireId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Questionnaire not found"));
    }

    public Question findOneQuestionById(UUID questionId) {
        return questionRepository.findById(questionId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Question not found"));
    }

    public void checkIfQuestionAnswerOptionCanBeAsigned (QuestionType questionType) {
        if(questionType == QuestionType.TEXT) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não é possível criar opções de respostas para perguntas do tipo texto");
        }
    }
}
