package com.aiiagcu.air.controller;

import com.aiiagcu.air.dto.UserDTO;
import com.aiiagcu.air.entity.Cell;
import com.aiiagcu.air.entity.Level;
import com.aiiagcu.air.service.AdminService;
import com.aiiagcu.air.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@AllArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/admin")
public class AdminController {

    private AdminService adminService;
    private UserService userService;
    @GetMapping("/user/all")
    public ResponseEntity<List<UserDTO>> findAllUser(){
        return ResponseEntity.ok(adminService.getAllUsers());
    }

    @GetMapping("/user/findById/{UserName}")
    public ResponseEntity<Optional<UserDTO>> findUserByUserName(@PathVariable("UserName") String UserName){
        return ResponseEntity.ok(adminService.getUserByUsername(UserName));
    }

    @GetMapping("/user/findByName/{Name}")
    public ResponseEntity<Optional<UserDTO>> findUserByName(@PathVariable("Name") String Name){
        return ResponseEntity.ok(adminService.getUserByName(Name));
    }

    @DeleteMapping("/user/delete/{id}")
    public ResponseEntity<UserDTO> deleteUser(@PathVariable("id") long id){
        UserDTO deleteUserDTO = userService.findById(id);

        adminService.deleteUser(deleteUserDTO);

        return deleteUserDTO.getId() == null ?
                ResponseEntity.badRequest().build():
                ResponseEntity.ok(deleteUserDTO);
    }

    @PatchMapping ("/user/updateCell/{id}")
    public ResponseEntity<UserDTO> updateCell(@PathVariable("id") long id, @RequestBody Cell cell) {
        UserDTO userDTO = userService.findById(id);

        //유저가 존재하지 않으면 예외
        if (userDTO == null) {
            return ResponseEntity.notFound().build();
        }

        adminService.updateCell(userDTO, cell);
        return ResponseEntity.ok(userDTO);
    }

    @PatchMapping ("/user/updateLevel/{id}")
    public ResponseEntity<UserDTO> updateLevel(@PathVariable("id") long id, @RequestBody Level level) {
        UserDTO userDTO = userService.findById(id);

        //유저가 존재하지 않으면 예외
        if (userDTO == null) {
            return ResponseEntity.notFound().build();
        }

        adminService.updateLevel(userDTO, level);
        return ResponseEntity.ok(userDTO);
    }

}
