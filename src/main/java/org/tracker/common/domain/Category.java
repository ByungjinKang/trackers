package org.tracker.common.domain;

import lombok.Data;

@Data
public class Category {
    private Long id;
    private String name;
    private String description;
    private String type;
    private String userId;
}
