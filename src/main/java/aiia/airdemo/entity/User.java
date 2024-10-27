package aiia.airdemo.entity;

import aiia.airdemo.entity.enumeration.Authority;
import aiia.airdemo.entity.enumeration.Position;
import aiia.airdemo.entity.enumeration.Role;
import aiia.airdemo.entity.enumeration.Unit;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    private String name;

    private String profileImage;

    private int grade;

    private String major;

    private Position position;

    private Long point;

    private Role role;

    @ElementCollection(fetch = FetchType.LAZY)
    private List<Authority> authorities = new ArrayList<>();

    @ElementCollection(fetch = FetchType.LAZY)
    private List<Unit> units = new ArrayList<>();
}
