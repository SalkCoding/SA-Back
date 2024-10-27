package aiia.airdemo.entity;

import aiia.airdemo.entity.enumeration.Authority;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "attendances")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Attendance extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "attendance")
    private List<AttendanceUser> attendanceUsers = new ArrayList<>(); // 해당 날짜에 출석해야 하는 유저 정보

    @Column(nullable = false)
    private LocalDateTime date; // 날짜

    private String attendanceCode;

    private LocalDateTime openAt; // 출석 시작 시간

    private LocalDateTime closeAt; // 지각 처리 시간

    private LocalDateTime cutOffAt; // 결석 처리 시간

}