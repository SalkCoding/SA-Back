package com.aiiagcu.air.entity;

import com.aiiagcu.air.dto.RecruitDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
public class Recruit extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recruit_id", updatable = false, nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDateTime startAt;

    @Column(nullable = false)
    private LocalDateTime endAt;

    @Column(nullable = false)
    private boolean isOpen;

    @Column(nullable = false)
    private boolean isArchived;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ApplicationFormQuestion> questions;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ApplicationForm> forms;

    public static Recruit toSaveEntity(RecruitDTO recruitDTO) {
        Recruit recruit = new Recruit();
        recruit.setId(recruitDTO.getId());
        recruit.setName(recruitDTO.getName());
        recruit.setStartAt(recruitDTO.getStartAt());
        recruit.setEndAt(recruitDTO.getEndAt());
        recruit.setOpen(true);
        recruit.setArchived(false);

        return recruit;
    }

}
