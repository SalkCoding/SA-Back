package com.aiiagcu.air.dto;

import com.aiiagcu.air.entity.Cell;
import com.aiiagcu.air.entity.Level;
import com.aiiagcu.air.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
public class UserDTO {

    //Auth information
    private Long id;
    private String studentNum;

    //User information
    private String name; //이름
    private String major; // 전공
    private int grade; //학년
    private String birth; // 생년월일
    private String phoneNumber; //전화번호
//    private String email; //이메일

    private Level level; // 동아리원 계급
    private Cell cell; // 동아리원 팀

    //private boolean verify;

    public static UserDTO toSaveDTO(User userEntity){

        UserDTO userDTO = new UserDTO();

        userDTO.setId(userEntity.getId());
        userDTO.setStudentNum(userEntity.getStudentNumber());
        userDTO.setName(userEntity.getName());
        userDTO.setMajor(userEntity.getMajor());
        userDTO.setGrade(userEntity.getGrade());
        userDTO.setBirth(userEntity.getBirth());
        userDTO.setPhoneNumber(userEntity.getPhoneNumber());
//        userDTO.setEmail(userEntity.getEmail());
        userDTO.setLevel(userEntity.getLevel());
        userDTO.setCell(userEntity.getCell());

        return userDTO;
    }

}
