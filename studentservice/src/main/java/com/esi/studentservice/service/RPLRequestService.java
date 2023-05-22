package com.esi.studentservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.esi.studentservice.dto.RPLRequestDto;
import com.esi.studentservice.model.RPLRequest;
import com.esi.studentservice.model.RPLRequestStatus;
import com.esi.studentservice.repository.RPLRequestRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RPLRequestService {

    @Autowired
    private RPLRequestRepository RPLRequestRepository;

    // Task3.4
    private final KafkaTemplate<String, RPLRequestDto> kafkaTemplate;
    // Task3.4

    public   List<RPLRequestDto> getAllRPLRequest(){
        List<RPLRequest> rPLRequests =  new ArrayList<>();
        RPLRequestRepository.findAll().forEach(rPLRequests::add);
        return rPLRequests.stream().map(this::mapToRPLRequestsDto).toList();
    }    
        
    private RPLRequestDto mapToRPLRequestsDto(RPLRequest rPLRequest) {
            return RPLRequestDto.builder()
                    .id(rPLRequest.getId())
                    .userId(rPLRequest.getUserId())
                    .courseToSubstituteName(rPLRequest.getCourseToSubstituteName())
                    .courseToSubstituteCode(rPLRequest.getCourseToSubstituteCode())
                    .courseToSubstituteVolume(rPLRequest.getCourseToSubstituteVolume())
                    .courseToBeSubstitutedName(rPLRequest.getCourseToBeSubstitutedName())
                    .courseToBeSubstitutedCode(rPLRequest.getCourseToBeSubstitutedCode())
                    .build();
    }

    // Task 3.1
    public void addRPLRequest(RPLRequestDto rPLRequestDto) {
        RPLRequest rPLRequest = RPLRequest.builder()
        .id(rPLRequestDto.getId())
                    .userId(rPLRequestDto.getUserId())
                    .courseToSubstituteName(rPLRequestDto.getCourseToSubstituteName())
                    .courseToSubstituteCode(rPLRequestDto.getCourseToSubstituteCode())
                    .courseToSubstituteVolume(rPLRequestDto.getCourseToSubstituteVolume())
                    .courseToBeSubstitutedName(rPLRequestDto.getCourseToBeSubstitutedName())
                    .courseToBeSubstitutedCode(rPLRequestDto.getCourseToBeSubstitutedCode())
                    .build();
                // Task 3.1
        // Task 3.2
        rPLRequest.setRPLRequestStatus(RPLRequestStatus.Submitted);
        rPLRequestDto.setRPLRequestStatus(RPLRequestStatus.Submitted);
        // Task 3.2

        // Task 3.3
        RPLRequestRepository.save(rPLRequest);
        // Task 3.3

        // Task 3.4
        kafkaTemplate.send("studentRequestCreatedTopic", rPLRequestDto);
        // Task 3.4
    }

    /* Task 6.3   */
    @KafkaListener(topics = "advisorTopic", groupId = "advisorGroup" )
    public void updateRequest(RPLRequestDto rPLRequestDto){
        RPLRequest rPLRequest = RPLRequest.builder()
        .id(rPLRequestDto.getId())
                    .userId(rPLRequestDto.getUserId())
                    .courseToSubstituteName(rPLRequestDto.getCourseToSubstituteName())
                    .courseToSubstituteCode(rPLRequestDto.getCourseToSubstituteCode())
                    .courseToSubstituteVolume(rPLRequestDto.getCourseToSubstituteVolume())
                    .courseToBeSubstitutedName(rPLRequestDto.getCourseToBeSubstitutedName())
                    .courseToBeSubstitutedCode(rPLRequestDto.getCourseToBeSubstitutedCode())
                    .rPLRequestStatus(rPLRequestDto.getRPLRequestStatus())

                    .build();

        RPLRequestRepository.save(rPLRequest);
    }
    /* Task 6.3   */
    
}
