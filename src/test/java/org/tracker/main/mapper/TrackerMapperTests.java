package org.tracker.main.mapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.tracker.common.config.RootConfig;
import org.tracker.main.domain.TrackerDTO;

import java.time.LocalDate;

@SpringBootTest(classes = RootConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
@Log4j2
public class TrackerMapperTests {

    @Setter(onMethod_ = @Autowired)
    private TrackerMapper mapper;

//    private static final Logger log = LoggerFactory.getLogger(ExpenseMapperTests.class);

    @Test
    public void testGetList() {
        mapper.getList().forEach(tracker -> log.info("" + tracker));
    }

    @Test
    public void testInsert() {
        TrackerDTO expense = new TrackerDTO();
        expense.setDescription("새로 작성하는 글");
        expense.setAmount(1000L);
        expense.setTrackerDate(LocalDate.parse("2021-09-01"));
        expense.setCategoryId(1L);
        expense.setAssetId(1L);

        mapper.insert(expense);
        log.info(String.valueOf(expense));
    }

    @Test
    public void testRead() {
        TrackerDTO expense = mapper.read(1L);
        log.info(String.valueOf(expense));
    }

    @Test
    public void testUpdate() {
        TrackerDTO expense = new TrackerDTO();
        expense.setId(1L);
        expense.setDescription("수정된 글");

        int count = mapper.update(expense);
        log.info("UPDATE COUNT: " + count);
    }

    @Test
    public void testDelete() {
        log.info("DELETE COUNT: " + mapper.delete(9L));
    }
}