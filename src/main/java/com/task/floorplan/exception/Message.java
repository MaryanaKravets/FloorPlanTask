package com.task.floorplan.exception;

public interface Message {

    String NO_SUCH_ROOM_EXCEPTION_MESSAGE = "Room with id '%s' not found";

    String INFINITE_AREA_EXCEPTION_MESSAGE = "Illegal. With assumption 1 this would lead to an infinite room.";

    String WALLS_INTERSECT_EXCEPTION_MESSAGE = "Walls intersect";

    String WALLS_DIAGONAL_EXCEPTION_MESSAGE = "One or more walls are diagonal";

    String NO_ENOUGH_CORNERS_EXCEPTION_MESSAGE = "At least 4 corners must be present. You entered '%s' points";
}
