package com.aiiagcu.air.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;

@Entity
@Getter
@Setter
public class ApplicationForm extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "application_form_id", updatable = false, nullable = false)
    private Long id;


    //유저
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false)
    private User user;

    // 모집 (2401,2402)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Recruit recruit;

    // 지원 분야
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Cell cell;

    // 답변들 리스트
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<ApplicationFormAnswer> answers;

}
