package com.quartz2.q2;

import javax.persistence.*;

@Entity
public class Fruit {
    
    @Id
    @GeneratedValue
    private long fruitId;
    private String fruitName;

    public long getFruitId() {
        return fruitId;
    }
 
    public void setFruitId(long fruitId) {
        this.fruitId = fruitId;
    }

    public String getFruitName() {
        return fruitName;
    }

    public void setFruitName(String fruitName) {
        this.fruitName = fruitName;
    }

}
