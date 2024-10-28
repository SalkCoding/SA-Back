package com.aiiagcu.air.controller;

import com.aiiagcu.air.dto.PeoplePageOutput;
import com.aiiagcu.air.dto.UserDTO;
import com.aiiagcu.air.entity.Cell;
import com.aiiagcu.air.entity.Level;
import com.aiiagcu.air.entity.User;
import com.aiiagcu.air.service.CellService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CellController {


    private final CellService cellService;

    @Autowired
    public CellController(CellService cellService){
        this.cellService = cellService;
    }


    //cell 별로 검색
    @GetMapping("/people/cell")
    public ResponseEntity<Map<Cell, List<UserDTO>>> getUsersByCell(){

        //cell 정보
        Map<Cell, List<UserDTO>> usersByCell = cellService.getUsersByCell();

        return ResponseEntity.ok(usersByCell);

    }

    //level 별로 검색
    @GetMapping("/people/level")
    public ResponseEntity<Map<Level, List<UserDTO>>> getUsersByLevel(){

        //cell 정보
        Map<Level, List<UserDTO>> usersByLevel = cellService.getUsersByLevel();

        return ResponseEntity.ok(usersByLevel);

    }

    @GetMapping("/people")
    public ResponseEntity<List<PeoplePageOutput>> getPeople() {
        return ResponseEntity.ok(cellService.getPeople());
    }

}
