package com.clonecoin.walletread.domain.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class ProfitDTO {

    @JsonProperty("profit")
    private Double profit;

    @JsonProperty("investment")
    private Double investment;

    @JsonProperty("localDate")
    //@DateTimeFormat(pattern = "yyyy-MM-dd")
    private String localDate;


    public ProfitDTO() {}

    public ProfitDTO(double profit, double investment,String localDate) {
        this.profit = profit;
        this.investment = investment;
        this.localDate = localDate;
    }

    @Override
    public String toString() {
        return "profit : " + profit + " , investment : " + investment + " , localDate : " + localDate;
    }
}
