package com.example.service;

import com.example.entity.Room;
import com.example.repository.RoomRepository;
import org.springframework.stereotype.Service;

@Service
public class RoomService {
    private RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public Room addRoom(Room room) {
        Room saved = roomRepository.save(room);
        return saved;
    }

    public Room findByPropertyIdAndType(long propertyId, String roomType) {
        Room byPropertyIdAndType = roomRepository.findByPropertyIdAndType(propertyId, roomType);
        return byPropertyIdAndType;
    }
}
