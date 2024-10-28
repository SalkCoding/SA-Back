package com.aiiagcu.air.dto;

import com.aiiagcu.air.entity.Cell;
import com.aiiagcu.air.entity.Level;
import com.aiiagcu.air.entity.User;
import lombok.Data;
import org.springframework.core.io.FileUrlResource;

import java.io.IOException;
import java.util.Base64;

@Data
public class PeoplePageOutput {
    private Long userId;
    private String name; //이름
    private String major; // 전공
    private Level level; // 동아리원 계급
    private Cell cell; // 동아리원 팀
//    private String email; //이메일
    private String img;

    public PeoplePageOutput(User user) {
        this.userId = user.getId();
        this.name = user.getName();
        this.major = user.getMajor();
        this.level = user.getLevel();
        this.cell = user.getCell();
//        this.email = user.getEmail();
        try {
            if (user.getImgPath() != null && !user.getImgPath().isEmpty()) {
                this.img = Base64.getEncoder().encodeToString(new FileUrlResource(user.getImgPath()).getContentAsByteArray());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
