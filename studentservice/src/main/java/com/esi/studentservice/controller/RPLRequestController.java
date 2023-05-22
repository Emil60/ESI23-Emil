package com.esi.studentservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.esi.studentservice.dto.RPLRequestDto;
import com.esi.studentservice.service.RPLRequestService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class RPLRequestController {

    @Autowired
    private RPLRequestService rPLRequestService;

    // Task 2
    @GetMapping("/rplrequests")
    public List<RPLRequestDto> getRPLRequest() {
        return rPLRequestService.getAllRPLRequest();
    }
    // Task 2

    @PostMapping("/rplrequests")
    public ResponseEntity<String>  addRPLRequest(@RequestBody RPLRequestDto rPLRequestDto) {
        rPLRequestService.addRPLRequest(rPLRequestDto);
    return ResponseEntity.ok("An RPL Request has been created");
    }
}
