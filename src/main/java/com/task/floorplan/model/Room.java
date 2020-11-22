package com.task.floorplan.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "rooms")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long roomId;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "room_id", referencedColumnName = "roomId")
    private List<Point> pointList;

    public Room() {
    }

    public Room(Long roomId, List<Point> pairList) {
        this.roomId = roomId;
        this.pointList = pairList;
    }

    public Room(List<Point> pointList) {
        this.pointList = pointList;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public List<Point> getPointList() {
        return pointList;
    }

    public void setPointList(List<Point> pointList) {
        this.pointList = pointList;
    }
}
