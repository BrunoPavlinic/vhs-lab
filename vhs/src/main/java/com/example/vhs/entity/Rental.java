package com.example.vhs.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "Rental")
@Data
@NoArgsConstructor

public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(message = "Rental id must not be null")
    @PositiveOrZero(message = "Rental id must be positive integer or 0")
    private int rentalId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    @NotNull(message = "Rental user must not be null")
    private User rentalUser;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "vhsId")
    @NotNull(message = "Rental vhs must not be null")
    private Vhs rentalVhs;

    @Column(nullable = false)
    @NotNull(message = "Rental start must not be null")
    private Date rentalStart;

    @Column(nullable = false)
    @NotNull(message = "Rental end must not be null")
    private Date rentalEnd;

    @Column
    private Date rentalReturned;

    @Column
    private Integer fee;

    public int getRentalId() {
        return rentalId;
    }

    public void setRentalId(int rentalId) {
        this.rentalId = rentalId;
    }

    public Date getRentalReturned() {
        return rentalReturned;
    }

    public Date getRentalEnd() {
        return rentalEnd;
    }

    public void setRentalReturned(Date rentalReturned) {
        this.rentalReturned = rentalReturned;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    public void setRentalUser(User rentalUser) {
        this.rentalUser = rentalUser;
    }

    public void setRentalVhs(Vhs rentalVhs) {
        this.rentalVhs = rentalVhs;
    }

    public void setRentalStart(Date rentalStart) {
        this.rentalStart = rentalStart;
    }

    public void setRentalEnd(Date rentalEnd) {
        this.rentalEnd = rentalEnd;
    }

    public Vhs getRentalVhs() {
        return rentalVhs;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + rentalId;
        result = 31 * result + rentalUser.hashCode();
        result = 31 * result + rentalVhs.hashCode();
        result = 31 * result + rentalStart.hashCode();
        result = 31 * result + rentalEnd.hashCode();
        result = 31 * result + rentalReturned.hashCode();
        result = 31 * result + fee.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof Rental)) {
            return false;
        }
        Rental rental = (Rental) obj;
        return rental.rentalId == rentalId &&
                rental.rentalUser.equals(rentalUser) &&
                rental.rentalVhs.equals((rentalVhs)) &&
                rental.rentalStart.equals(rentalStart) &&
                rental.rentalEnd.equals(rentalEnd) &&
                rental.rentalReturned.equals(rentalReturned) &&
                rental.fee == fee;
    }

    @Override
    public String toString() {
        return String.format("Rental id - %d, Rental user: %d, Rental vhs: %d, Rental start - %s, Rental end - %s, Rental returned - %s", rentalId, rentalUser.getUserId(), rentalVhs.getVhsId(), rentalStart, rentalEnd, rentalReturned);
    }
}