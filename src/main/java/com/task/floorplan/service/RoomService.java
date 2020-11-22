package com.task.floorplan.service;

import com.task.floorplan.model.Room;

import java.util.List;

public interface RoomService {

    Room getByRoomId(Long roomId);

    List<Room> getRooms();

    Room validateRoom(Room room);
}
