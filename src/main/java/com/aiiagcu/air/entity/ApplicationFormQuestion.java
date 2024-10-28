package com.aiiagcu.air.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Getter
@Setter
public class ApplicationFormQuestion extends BaseEntity{
    // Question 고유의 pk 값
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "application_form_question_id", updatable = false, nullable = false)
    private Long id;

    // ex) 2401, 2402 모집
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Recruit recruit;

    // 지원분야
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Cell position;

    // 진짜 Question
    @Column(nullable = false)
    private String question;

}
