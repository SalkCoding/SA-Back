package com.aiiagcu.air.entity;

import com.aiiagcu.air.dto.ProfileDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
public class Profile extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profile_id", updatable = false, nullable = false)
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

    public static Profile toSaveEntity(ProfileDTO profileDTO) {
        Profile profile = new Profile();
        profile.setId(profileDTO.getId());
        profile.setName(profileDTO.getName());
        profile.setStartAt(profileDTO.getStartAt());
        profile.setEndAt(profileDTO.getEndAt());
        profile.setOpen(true);
        profile.setArchived(false);

        return profile;
    }

}
