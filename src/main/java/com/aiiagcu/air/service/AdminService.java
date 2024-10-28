package com.aiiagcu.air.service;

import com.aiiagcu.air.dto.UserDTO;
import com.aiiagcu.air.entity.Cell;
import com.aiiagcu.air.entity.Level;
import com.aiiagcu.air.entity.User;
import com.aiiagcu.air.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.List;

@AllArgsConstructor
@Service
public class AdminService {

    private final UserRepository userRepository;

    // 1. 모든 회원 정보 반환
    public List<UserDTO> getAllUsers(){
        List<User> userList = userRepository.findAll();
        List<UserDTO> userDTOList = new ArrayList<>();

        for(User userEntity : userList){
            userDTOList.add(UserDTO.toSaveDTO(userEntity));
        }
        return userDTOList;
    }

    // 2. 이름에 따른 검색 기능

    //id 에 따른 검색 기능
    public Optional<UserDTO> getUserByUsername(String Username){
        Optional<User> user = userRepository.findByStudentNumber(Username);
        return user.map(UserDTO::toSaveDTO);
    }


    //이름에 따른 검색 기능
    public Optional<UserDTO> getUserByName(String name){
        Optional<User> user = userRepository.findByname(name);
        return user.map(UserDTO::toSaveDTO);
    }


    // 3. 회원 계정 삭제
    public void deleteUser(UserDTO userDTO){
        User deleteUser = userRepository.findById(userDTO.getId())
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + userDTO.getId()));

        userRepository.delete(deleteUser);
    }

    // 4. cell 바꾸기
    public void updateCell(UserDTO userDTO, Cell cell){
        userRepository.updateUserCell(cell, userDTO.getId());
    }

    // 5. level 바꾸기
    public void updateLevel(UserDTO userDTO, Level level){
        userRepository.updateUserLevel(level, userDTO.getId());
    }

}


