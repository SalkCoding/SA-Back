package com.aiiagcu.air.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.*;

@Entity
@Getter
@Setter
public class ApplicationFormAnswer extends BaseEntity{
    // Answer 가 있는 form
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private ApplicationForm form;

    // Answer 에 해당하는 질문
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ApplicationFormQuestion question;

    // 진짜 Answer
    @Column(nullable = false)
    private String answer;

}
