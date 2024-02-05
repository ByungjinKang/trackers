package org.tracker.main.domain;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class TrackerDTO {
    private Long id;
    private String description;
    private Long amount;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate trackerDate;
    private String categoryName;
    private String AssetName;
    private String typeName;
    private Long categoryId;
    private Long assetId;
    private Long typeId;
    private Long userId;
}
