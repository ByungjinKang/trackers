package org.tracker.expense.domain;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class ExpenseDTO {

    private Long id; // 지출 항목의 고유 식별자
    private String description; // 지출 내용 또는 설명
    private double amount; // 지출 금액
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date; // 지출 일자
    private Long category; // 해당 지출이 속한 카테고리
    private String paymentMethod; // 결제 수단 (예: 현금, 카드)
    private String note;
    private String icon;
    // 기타 필요한 속성들...
}
