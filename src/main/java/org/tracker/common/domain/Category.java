package org.tracker.common.domain;

import lombok.Data;

@Data
public class Category {
    private Long Id;
    private String Name;
    private String description;
    private String type;
}
