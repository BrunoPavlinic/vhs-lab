package com.example.vhs.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;

@Entity
@Table(name = "Vhs")
@Data
@NoArgsConstructor
public class Vhs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(message = "Vhs id must not be null")
    @PositiveOrZero(message = "Vhs id must be positive number or 0")
    private int vhsId;

    @Column(nullable = false)
    @NotNull(message = "Vhs name must not be null")
    private String name;

    @Column(nullable = false)
    @Positive(message = "Year must be a positive number")
    private Integer year;

    @Column(nullable = false)
    @NotNull(message = "Vhs availability must not be null")
    private boolean available;

    public int getVhsId() {
        return vhsId;
    }

    public void setVhsId(int vhsId) {
        this.vhsId = vhsId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + vhsId;
        result = 31 * result + name.hashCode();
        result = 31 * result + year;
        result = 31 * result + String.valueOf(available).hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof Vhs)) {
            return false;
        }
        Vhs vhs = (Vhs) obj;
        return vhs.vhsId == vhsId &&
                vhs.name.equals(name) &&
                vhs.year == year &&
                String.valueOf(vhs.available).equals(String.valueOf(available));
    }

    @Override
    public String toString() {
        return String.format("Vhs id - %d, Vhs name: %s, Vhs year: %d, Vhs available - %s", vhsId, name, year, String.valueOf(available));
    }
}