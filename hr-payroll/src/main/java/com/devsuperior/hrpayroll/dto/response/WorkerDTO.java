package com.devsuperior.hrpayroll.dto.response;

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
public class WorkerDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -6193352597473370027L;

    private Long id;
    private String name;
    private Double dailyIncome;

}
