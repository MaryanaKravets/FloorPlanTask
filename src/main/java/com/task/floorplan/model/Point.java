package com.task.floorplan.model;

import javax.persistence.*;

@Entity
@Table(name = "points")
public class Point {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer X;
    private Integer Y;

    public Point(Integer X, Integer Y) {
        this.X = X;
        this.Y = Y;
    }

    public Point() {
    }

    public Integer getX() {
        return X;
    }

    public void setX(Integer firstValue) {
        this.X = firstValue;
    }

    public Integer getY() {
        return Y;
    }

    public void setY(Integer secondValue) {
        this.Y = secondValue;
    }
}
