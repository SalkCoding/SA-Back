package aiia.airdemo.entity;

import aiia.airdemo.entity.enumeration.AttendanceStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "attendance_users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AttendanceUser extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attendence_id")
    private Attendance attendance; // 출석 정보

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user; // 유저 정보

    private LocalDateTime attendanceAt; // 출석 시간

    @Enumerated(EnumType.STRING)
    private AttendanceStatus status = AttendanceStatus.ABSENCE; // 출석 상태 (기본 결석)

    private String note; // 비고

}
