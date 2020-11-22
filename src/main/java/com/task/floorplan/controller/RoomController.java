package com.task.floorplan.controller;

import com.task.floorplan.model.Room;
import com.task.floorplan.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class RoomController {

    private RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping("/{id}")
    public String getRoomById(@PathVariable Long id, Model model) {
        model.addAttribute("room", roomService.getByRoomId(id));
        return "room";
    }

    @GetMapping
    @ResponseBody
    public List<Room> getRooms() {
        return roomService.getRooms();
    }

    @ResponseBody
    @PostMapping("/validateRoom")
    public Room validateRoom(@RequestBody Room room) {
        return roomService.validateRoom(room);
    }

}
