package com.ssafy.a107.api.controller;

import com.ssafy.a107.api.request.ItemCreateReq;
import com.ssafy.a107.api.request.ItemUpdateReq;
import com.ssafy.a107.api.request.UserItemReq;
import com.ssafy.a107.api.response.ItemRes;
import com.ssafy.a107.api.service.ItemService;
import com.ssafy.a107.common.exception.NotFoundException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Api(value = "아이템 API", tags = {"Item"})
@RestController
@RequestMapping("/api/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping
    @ApiOperation(value = "전체 아이템 리스트 조회")
    public ResponseEntity<?> getAllItemList() {
        List<ItemRes> items = itemService.getAllItems();

        if(items == null || items.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }

        return ResponseEntity.status(HttpStatus.OK).body(items);
    }

    @GetMapping("/{userSeq}")
    @ApiOperation(value = "특정 유저의 아이템 리스트 조회", notes = "유저 시퀀스로 아이템 리스트 제공")
    public ResponseEntity<?> getItemListByUserSeq(@PathVariable Long userSeq) {
        List<ItemRes> itemList = itemService.getItemByUserSeq(userSeq);

        if(itemList != null && !itemList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(itemList);
        }

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @PostMapping("/userItem")
    @ApiOperation(value = "특정 유저에게 아이템을 생성함")
    public ResponseEntity<?> addItemToUser(@RequestBody UserItemReq userItemReq) {
        try {
            itemService.createUserItem(userItemReq);
            return ResponseEntity.status(HttpStatus.CREATED).body(null);
        }
        catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping
    @ApiOperation(value = "새로운 아이템 생성 - 관리자만 사용 가능")
    public ResponseEntity<?> createItem(@RequestPart MultipartFile multipartFile, @RequestParam Byte type, @RequestParam String name) {
        ItemCreateReq itemCreateReq = new ItemCreateReq(type, name, multipartFile);

        try {
            itemService.createItem(itemCreateReq);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{ msg: 'Image Upload Fail'}");
        }
    }

    @PutMapping("/{itemSeq}")
    @ApiOperation(value = "아이템 업데이트 - 관리자만 사용 가능")
    public ResponseEntity<?> updateItem(@PathVariable Long itemSeq, @RequestPart MultipartFile multipartFile, @RequestParam String name) {
        ItemUpdateReq itemUpdateReq = new ItemUpdateReq(itemSeq, name, multipartFile);

        try {
            itemService.updateItem(itemUpdateReq);
            return ResponseEntity.status(HttpStatus.CREATED).body(null);
        }
        catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{ msg: 'Image Upload Fail'}");
        }
    }

    @DeleteMapping("/{itemSeq}")
    @ApiOperation(value = "아이템 삭제 - 관리자만 사용 가능")
    public ResponseEntity<?> deleteItem(@PathVariable Long itemSeq) {
        itemService.deleteItem(itemSeq);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
