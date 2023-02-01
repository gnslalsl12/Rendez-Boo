package com.ssafy.a107.api.controller;

import com.ssafy.a107.api.request.JoinReq;
import com.ssafy.a107.api.request.LoginReq;
import com.ssafy.a107.api.response.UserRes;
import com.ssafy.a107.api.service.UserService;
import com.ssafy.a107.common.exception.NotFoundException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@Api(value = "유저 API", tags = {"User"})
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    // 유저 정보 조회
    @GetMapping("/{userSeq}")
    @ApiOperation(value = "유저 정보 조회", notes = "Seq로 유저 정보 제공")
    public ResponseEntity<?> getUserInfo(@PathVariable Long userSeq) throws NotFoundException{
        UserRes user = userService.getUserBySeq(userSeq);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    // 회원가입
    @PostMapping("/join")
    @ApiOperation(value = "유저 회원가입", notes = "유저 회원가입")
    public ResponseEntity<?> joinUser(@RequestBody JoinReq joinReq) {
        Boolean dupCheck = userService.checkEmailDuplicate(joinReq.getEmail());

        if(!dupCheck) {
            userService.createUser(joinReq);
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        else {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    // 로그인
    @PostMapping("/login")
    @ApiOperation(value = "유저 로그인", notes = "유저 로그인")
    public ResponseEntity<?> login(@RequestBody LoginReq loginReq) throws NotFoundException {
        //UserRes findUser = userService.getUserByEmail(loginReq.getEmail());

        // 비밀번호가 일치하면
        //if(findUser != null && passwordEncoder.matches(loginReq.getPassword(), findUser.getPassword())) {
//            return ResponseEntity.status(HttpStatus.OK).build();
        //}
//        else {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
//        }
    }

    // 아이디 중복체크
    @GetMapping("/check/{email}")
    @ApiOperation(value = "이메일 중복체크", notes = "이메일 중복 체크")
    public ResponseEntity<?> checkEmail(@PathVariable String email) {
        if(userService.checkEmailDuplicate(email)) return ResponseEntity.status(HttpStatus.CONFLICT).build();
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/{userSeq}/friends")
    @ApiOperation(value="유저의 친구 목록", notes = "유저의 친구 목록")
    public ResponseEntity<?> getFriends(@PathVariable Long userSeq) throws NotFoundException{
        return ResponseEntity.status(HttpStatus.OK).body(userService.getFriends(userSeq));
    }
    @GetMapping("/{userSeq}/blockeds")
    @ApiOperation(value="유저의 친구 목록", notes = "유저의 친구 목록")
    public ResponseEntity<?> getBlockeds(@PathVariable Long userSeq) throws NotFoundException{
        return ResponseEntity.status(HttpStatus.OK).body(userService.getBlockeds(userSeq));
    }
}