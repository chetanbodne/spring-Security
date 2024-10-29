package com.example.controller;

import com.example.entity.Room;
import com.example.repository.RoomRepository;
import com.example.service.PropertyService;
import com.example.service.RoomService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/booking")
public class BookingController {
    private RoomService roomService;

    public BookingController(RoomService roomService) {
        this.roomService = roomService;


    }

    @PostMapping("/createBooking")
    public ResponseEntity<?> createBooking(@RequestParam long propertyId, @RequestParam String roomType) {
        Room byPropertyIdAndType = roomService.findByPropertyIdAndType(propertyId, roomType);
      //  System.out.println(byPropertyIdAndType);
        if (byPropertyIdAndType.getCount() == 0) {
            return new ResponseEntity<>("Room are not Book", HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
          int val=  byPropertyIdAndType.getCount();
            byPropertyIdAndType.setCount(val-1);
            roomService.addRoom(byPropertyIdAndType);
            return new ResponseEntity<>("Room Are Booking", HttpStatus.CREATED);
        }
    }

}
