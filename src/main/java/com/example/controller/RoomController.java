package com.example.controller;

import com.example.entity.Property;
import com.example.entity.Room;
import com.example.service.PropertyService;
import com.example.service.RoomService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/room")
public class RoomController {
    private RoomService roomService;
private PropertyService propertyService;
    public RoomController(RoomService roomService, PropertyService propertyService) {
        this.roomService = roomService;
        this.propertyService = propertyService;
    }
    @PostMapping
public ResponseEntity<Room>addRoom(@RequestBody Room room, @RequestParam long propertyId){
    Property property = propertyService.findById(propertyId);
    room.setProperty(property);

    Room added = roomService.addRoom(room);
    return new ResponseEntity<>(added, HttpStatus.CREATED);

}

}
