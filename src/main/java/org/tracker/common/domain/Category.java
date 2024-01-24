package org.tracker.common.domain;

import lombok.Data;

@Data
public class Category {
    private Long categoryId; // 카테고리의 고유 식별자
    private String name; // 카테고리 이름
    private String description; // 카테고리 설명
    // 기타 필요한 속성들...
}
