package com.ssafy.a107.api.service;

import com.ssafy.a107.api.request.MultyMeetingRoomReq;
import com.ssafy.a107.api.response.MultiMeetingRoomRes;
import com.ssafy.a107.common.exception.NotFoundException;
import com.ssafy.a107.db.repository.MultiMeetingRoomUserRepository;
import com.ssafy.a107.db.repository.MultiMeetingRoomRepository;
import com.ssafy.a107.db.repository.UserRepository;
import io.openvidu.java.client.OpenVidu;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Slf4j
@Service
@RequiredArgsConstructor
public class MultiMeetingRoomServiceImpl implements MultiMeetingRoomService {

    @Value("${openvidu.url}")
    private String OPENVIDU_URL;

    @Value("${openvidu.secret}")
    private String OPENVIDU_SECRET;

    private OpenVidu openVidu;
    @PostConstruct
    public void init() {
        this.openVidu = new OpenVidu(OPENVIDU_URL, OPENVIDU_SECRET);
    }

    MultiMeetingRoomRepository multyMeetingRoomRepository;
    MultiMeetingRoomUserRepository multiMeetingRoomUserRepository;

    UserRepository userRepository;


    @Override
    public Long saveMultiMeetingRoom(MultyMeetingRoomReq multyMeetingRoomReq) {


        return null;
    }
    @Override
    public MultiMeetingRoomRes getMultiMeetingRoom(Long roomSeq) throws NotFoundException {
        int maleNum=0, femaleNum=0;
        return new MultiMeetingRoomRes(multyMeetingRoomRepository.findById(roomSeq)
                .orElseThrow(()->new NotFoundException("Invalid multiMeetingRoom sequence")),
                maleNum, femaleNum
        );
    }

    @Override
    public Long joinMultiMeetingRoom(Long meetingRoomSeq, Long userSeq) throws NotFoundException {
        return null;
    }
}
