package com.aiiagcu.air.service;

import com.aiiagcu.air.dto.PeoplePageOutput;
import com.aiiagcu.air.dto.UserDTO;
import com.aiiagcu.air.entity.Cell;
import com.aiiagcu.air.entity.Level;
import com.aiiagcu.air.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CellService {

    private final UserRepository userRepository;

    //cell 별로 검색
    public Map<Cell, List<UserDTO>> getUsersByCell() {
        List<UserDTO> allUsers = userRepository.findAll().stream()
                .map(UserDTO::toSaveDTO)
                .collect(Collectors.toList());

        Map<Cell, List<UserDTO>> usersByCell = new HashMap<>();

        for (UserDTO user : allUsers) {
            Cell cell = user.getCell();

            if (cell == Cell.BRAND_DESIGN || cell == Cell.UI_UX_DESIGN) {
                cell = Cell.DESIGN;
            }

            usersByCell.computeIfAbsent(cell, k -> new ArrayList<>()).add(user);
        }

        return usersByCell;

    }


    //level 별로 검색
    public Map<Level, List<UserDTO>> getUsersByLevel() {
        List<UserDTO> allUsers = userRepository.findAll().stream()
                .map(UserDTO::toSaveDTO)
                .collect(Collectors.toList());

        Map<Level, List<UserDTO>> usersByLevel = new HashMap<>();

        for (UserDTO user : allUsers) {
            Level level = user.getLevel();
            usersByLevel.computeIfAbsent(level, k -> new ArrayList<>()).add(user);

        }

        return usersByLevel;

    }

    public List<PeoplePageOutput> getPeople() {
        return userRepository.findAll().stream().map(PeoplePageOutput::new).toList();
    }
}
