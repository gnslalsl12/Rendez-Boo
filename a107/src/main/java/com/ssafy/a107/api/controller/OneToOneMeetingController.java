package com.ssafy.a107.api.controller;

import com.ssafy.a107.api.response.MeetingRoomRes;
import com.ssafy.a107.api.service.OneToOneMeetingService;
import com.ssafy.a107.common.exception.NotFoundException;
import io.openvidu.java.client.OpenViduHttpException;
import io.openvidu.java.client.OpenViduJavaClientException;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("일대일 미팅방 관련 컨트롤러")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/onetoone")
public class OneToOneMeetingController {

    @Autowired
    OneToOneMeetingService oneToOneMeetingService;

    @PostMapping("/{userSeq}")
    public ResponseEntity<?> joinMatch(@PathVariable Long userSeq) throws OpenViduJavaClientException, OpenViduHttpException, NotFoundException {
        MeetingRoomRes meetingRoomRes = oneToOneMeetingService.joinMatch(userSeq);
        return ResponseEntity.status(HttpStatus.OK).body(meetingRoomRes);
    }
}
