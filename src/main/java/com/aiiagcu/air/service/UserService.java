package com.aiiagcu.air.service;

import com.aiiagcu.air.dto.*;
import com.aiiagcu.air.entity.User;
import com.aiiagcu.air.repository.UserRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final StringRedisTemplate redisTemplate;

    public void signup(SignupInput signupInput) {
        if (userRepository.existsByStudentNumber(signupInput.getStudentNum())) {
            throw new EntityExistsException("이미 회원가입한 학번입니다.");
        }
        userRepository.save(User.createUserForSignup(signupInput));
    }

    public void freshmanSignup(FreshmanSignupInput signupInput) {
        if (userRepository.existsByStudentNumber(signupInput.getStudentNum())) {
            throw new EntityExistsException("이미 회원가입한 학번입니다.");
        }
        userRepository.save(User.createUserForFreshmanSignup(signupInput));
    }

    public UserDTO login(LoginInput loginInput, HttpServletRequest servletRequest) {
        User user = userRepository.findByStudentNumberAndPassword(loginInput.getStudentNum(), loginInput.getPassword());
        if (user == null) {
            throw new EntityNotFoundException("아이디 또는 비밀번호가 틀렸습니다.");
        } else {
            HttpSession session = servletRequest.getSession();
            session.setAttribute("loginUser", user);

            return UserDTO.toSaveDTO(user);
        }
    }

    public UserDTO findById(long id) {
        User findUser = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + id));

        return UserDTO.toSaveDTO(findUser);
    }

    @Transactional
    public void removeUser(User loginUser) {
        userRepository.delete(loginUser);
    }
}