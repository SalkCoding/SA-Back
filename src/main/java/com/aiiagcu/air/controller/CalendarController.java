package com.aiiagcu.air.controller;

import com.aiiagcu.air.dto.CalendarOutput;
import com.aiiagcu.air.dto.CalenderInput;
import com.aiiagcu.air.entity.User;
import com.aiiagcu.air.service.CalendarService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CalendarController {
    private final CalendarService calendarService;
    
    
    @GetMapping("/calendar/{date}")
    public List<CalendarOutput> calendarList(@PathVariable Integer date) throws GeneralSecurityException, IOException {
        return calendarService.findCalendar(date);
    }
    
    
    @PostMapping("/calendar")
    public CalendarOutput calendarAdd(@RequestBody CalenderInput calenderInput, @SessionAttribute(name = "loginUser", required = false) User user) throws GeneralSecurityException, IOException {
        return calendarService.addCalendar(calenderInput);
    }
    
    @PatchMapping("/calendar")
    public CalendarOutput calendarUpdate(@RequestBody CalenderInput calenderInput, @SessionAttribute(name = "loginUser", required = false) User user) throws GeneralSecurityException, IOException {
        return calendarService.updateCalendar(calenderInput);
    }
    
    @DeleteMapping("/calendar/{id}")
    public void calendarDelete(@PathVariable String id, @SessionAttribute(name = "loginUser", required = false) User user) throws GeneralSecurityException, IOException {
        calendarService.deleteCalendar(id);
    }
}
