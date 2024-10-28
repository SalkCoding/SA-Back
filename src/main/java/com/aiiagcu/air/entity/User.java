package com.aiiagcu.air.entity;

import com.aiiagcu.air.dto.FreshmanSignupInput;
import com.aiiagcu.air.dto.SignupInput;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;


@Entity
@ToString
@NoArgsConstructor
@Getter
public class User extends BaseEntity implements Serializable {

    @Id
    @Column(updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Auth information
    private String studentNumber;
    private String password;
    private String imgPath;

    //User information
    private String name; //이름
    private String major; // 전공
    private int grade; //학년
    private String birth; // 생년월일
    private String phoneNumber; //전화번호
//    @Email
//    private String email; //이메일


    //enum
    @Enumerated(value = EnumType.STRING)
    private Level level; // 동아리원 계급
    @Enumerated(value = EnumType.STRING)
    private Cell cell; // 동아리원 팀
    private boolean verify;

    public void level(Level level, Cell cell){
        this.level = level;
        this.cell = cell;

    }

    public boolean equals(User compareUser) {
        return this.getId().equals(compareUser.getId());
    }

    public static User createUserForSignup(SignupInput signupInput) {

        User user = new User();
//        user.userId = signupInput.getUserId;
        user.name = signupInput.getName();
        user.studentNumber = signupInput.getStudentNum();
        user.password = signupInput.getPassword();
//        user.email = signupInput.getGachonEmail();
        return user;
    }

    public static User createUserForFreshmanSignup(FreshmanSignupInput signupInput) {
        User user = new User();
        user.name = signupInput.getName();
        user.studentNumber = signupInput.getStudentNum();
        user.password = signupInput.getPassword();
        return user;
    }
}
