package com.task.floorplan.service.impl;

import com.task.floorplan.exception.*;
import com.task.floorplan.model.Point;
import com.task.floorplan.model.Room;
import com.task.floorplan.repository.RoomRepository;
import com.task.floorplan.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomServiceImpl implements RoomService, Message {

    private RoomRepository roomRepository;

    @Autowired
    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public Room getByRoomId(Long roomId) {
        Room room = roomRepository.findByRoomId(roomId)
                .orElseThrow(() -> new NoSuchRoomException(String.format(NO_SUCH_ROOM_EXCEPTION_MESSAGE, roomId)));

        return room;
    }

    @Override
    public List<Room> getRooms() {
        return roomRepository.findAll();
    }

    @Override
    public Room validateRoom(Room rooms) {
        Room room = new Room(rooms.getPointList().stream().map((i) -> new Point(i.getX(), i.getY()))
                .collect(Collectors.toList()));
        int i = new ArrayList<>(room.getPointList()).size();
        if (i < 4) {
            throw new NoEnoughCornersException(String.format(NO_ENOUGH_CORNERS_EXCEPTION_MESSAGE, i));
        } else if (!validOnDiagonal(room)) {
            throw new WallsDiagonalException(WALLS_DIAGONAL_EXCEPTION_MESSAGE);
        } else if (!validOnClockwiseDirection(room)) {
            throw new InfiniteAreaException(INFINITE_AREA_EXCEPTION_MESSAGE);
        } else if (!validateOnNonIntersectionPolygon(room)) {
            throw new WallsIntersectException(WALLS_INTERSECT_EXCEPTION_MESSAGE);
        } else {
            roomRepository.save(room);
            return room;
        }
    }

    private boolean validOnDiagonal(Room room) {
        boolean flag = true;
        for (int i = 0; i < room.getPointList().size() - 1; i++) {
            if ((room.getPointList().get(i).getX() == room.getPointList().get(i + 1).getX())
                    ||
                    (room.getPointList().get(i).getY() == room.getPointList().get(i + 1).getY())) {
            } else {
                flag = false;
            }
        }
        return flag;
    }

    private boolean validOnClockwiseDirection(Room room) {
        int i = room.getPointList().size();
        boolean flag = true;

        int sum = (room.getPointList().get(i - 1).getX() - room.getPointList().get(0).getX()) *
                (room.getPointList().get(i - 1).getY() + room.getPointList().get(0).getY());
        for (int k = 0; k < i - 1; k++) {
            sum = sum + (room.getPointList().get(k + 1).getX() - room.getPointList().get(k).getX()) *
                    (room.getPointList().get(k + 1).getY() + room.getPointList().get(k).getY());
        }
        if (sum < 0) {
            flag = false;
        }
        return flag;

    }

    private boolean validateOnNonIntersectionPolygon(Room room) {
        int i = room.getPointList().size();
        boolean flag = true;
        List<Point> list = room.getPointList();

        for (int k = 0; k < i - 2; k++) {
            Line2D line1 = new Line2D.Float(list.get(k).getX(), list.get(k).getY(), list.get(k + 1).getX(), list.get(k + 1).getY());
            if (k != 0) {
                Line2D line = new Line2D.Float(list.get(0).getX(), list.get(0).getY(), list.get(i - 1).getX(), list.get(i - 1).getY());
                if (line1.intersectsLine(line)) {
                    flag = false;
                }
            }
            for (int j = k + 2; j < i - 1; j++) {
                Line2D line2 = new Line2D.Float(list.get(j).getX(), list.get(j).getY(), list.get(j + 1).getX(), list.get(j + 1).getY());
                if (line1.intersectsLine(line2)) {
                    flag = false;
                }
            }
        }
        return flag;
    }
}
