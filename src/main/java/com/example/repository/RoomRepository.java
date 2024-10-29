package com.example.repository;

import com.example.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RoomRepository extends JpaRepository<Room, Long> {
    @Query("select r from Room r where r.property.id=:propertyId and r.type=:roomType")
    Room findByPropertyIdAndType(@Param("propertyId")long propertyId,String roomType);
}

