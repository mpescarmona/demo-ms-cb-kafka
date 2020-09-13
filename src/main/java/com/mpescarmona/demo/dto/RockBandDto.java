package com.mpescarmona.demo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document("RockBand")
public class RockBandDto {
    @Id
    String id;
    String rockBand;
    Integer likes;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSSX", timezone = "UTC")
    Instant created;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSSX", timezone = "UTC")
    Instant updated;
}
