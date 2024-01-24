package org.tracker.expense.mapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.tracker.common.config.RootConfig;
import org.tracker.common.domain.Category;
import org.tracker.expense.domain.ExpenseDTO;

import java.time.LocalDate;

@SpringBootTest(classes = RootConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
@Log4j2
public class ExpenseMapperTests {

    @Setter(onMethod_ = @Autowired)
    private ExpenseMapper mapper;

//    private static final Logger log = LoggerFactory.getLogger(ExpenseMapperTests.class);

    @Test
    public void testGetList() {
        mapper.getList().forEach(expense -> log.info("" + expense));
    }

    @Test
    public void testInsert() {
        ExpenseDTO expense = new ExpenseDTO();
        expense.setDescription("새로 작성하는 글");
        expense.setAmount(1000);
        expense.setExpenseDate(LocalDate.parse("2021-09-01"));
        expense.setCategory(1L);
        expense.setPaymentMethod("newbie");
        expense.setLocation("newbie");

        mapper.insert(expense);
        log.info(String.valueOf(expense));
    }

    @Test
    public void testRead() {
        ExpenseDTO expense = mapper.read(1L);
        log.info(String.valueOf(expense));
    }

    @Test
    public void testUpdate() {
        ExpenseDTO expense = new ExpenseDTO();
        expense.setId(1L);
        expense.setDescription("수정된 글");
        expense.setAmount(2000);
        expense.setExpenseDate(LocalDate.parse("2021-09-02"));
        expense.setCategory(2L);
        expense.setPaymentMethod("수정된 글");
        expense.setLocation("수정된 글");

        int count = mapper.update(expense);
        log.info("UPDATE COUNT: " + count);
    }

    @Test
    public void testDelete() {
        log.info("DELETE COUNT: " + mapper.delete(9L));
    }
}