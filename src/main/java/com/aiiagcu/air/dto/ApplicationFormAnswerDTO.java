package com.aiiagcu.air.dto;

import com.aiiagcu.air.entity.ApplicationFormAnswer;
import lombok.Data;

import java.io.Serializable;

/**
 * DTO for {@link ApplicationFormAnswer}
 */
@Data
public class ApplicationFormAnswerDTO implements Serializable {

    private long questionId;
    private String answer;

    public ApplicationFormAnswerDTO toSaveDTO(ApplicationFormAnswer applicationFormAnswer) {
        ApplicationFormAnswerDTO dto = new ApplicationFormAnswerDTO();
        dto.setQuestionId(applicationFormAnswer.getQuestion().getId());
        dto.setAnswer(applicationFormAnswer.getAnswer());
        return dto;
    }

}
