package com.esi.adviserservice.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.esi.adviserservice.dto.RPLRequestDto;
import com.esi.adviserservice.dto.RPLRequestStatus;
import com.esi.adviserservice.model.RPLAdvisor;
import com.esi.adviserservice.repository.RPLAdvisorRepository;

import lombok.RequiredArgsConstructor;



@RequiredArgsConstructor
@Service
public class RPLAdvisorService {
 
    @Autowired
    private RPLAdvisorRepository rPLAdvisorRepository;
    
    private final KafkaTemplate<String, RPLRequestDto> kafkaTemplate;
    
    // Task 5
    
    @KafkaListener(topics = "studentRequestCreatedTopic", groupId = "requestEventGroup" )

    public void updateRPLResponse(RPLRequestDto rPLRequestDto) {
        RPLAdvisor rplAdvisor = rPLAdvisorRepository.findById(rPLRequestDto.getUserId()).get();

        rplAdvisor.setAdvisoryDescription(null);
        //Task6
        rplAdvisor.setRPLRequestStatus(RPLRequestStatus.UnderReview);
        
        //Task6
        rPLAdvisorRepository.save(rplAdvisor);

    }
    // Task 5

}

