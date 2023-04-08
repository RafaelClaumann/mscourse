package com.devsuperior.hrpayroll.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@ToString
public class Payment implements Serializable {

    @Serial
    private static final long serialVersionUID = 199078195899305607L;

    private String name;

    private Double dailyIncome;

    private Integer days;

    public double getTotal() {
        return days * dailyIncome;
    }

}
