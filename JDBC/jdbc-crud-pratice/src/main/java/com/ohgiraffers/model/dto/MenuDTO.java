package com.ohgiraffers.model.dto;

import java.sql.Timestamp;

public class MenuDTO {

  private int id;
  private String name;
  private double price;
  private String category;
  private int is_available;
  private Timestamp created_at;
  private Timestamp updated_at;

  public MenuDTO() {}

    public MenuDTO(int id, String name, double price, String category, int is_available, Timestamp created_at, Timestamp updated_at) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.is_available = is_available;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getIs_available() {
        return is_available;
    }

    public void setIs_available(int is_available) {
        this.is_available = is_available;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }

    @Override
    public String toString() {
        return "MenuDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                ", is_available=" + is_available +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                '}';
    }
}
