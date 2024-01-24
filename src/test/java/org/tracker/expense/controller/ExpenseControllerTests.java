package org.tracker.expense.controller;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(
        classes = {org.tracker.common.config.RootConfig.class,
                org.tracker.common.config.ServletConfig.class})
@Log4j
public class ExpenseControllerTests {

    @Setter(onMethod_ = {@Autowired})
    private WebApplicationContext ctx;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
    }

    @Test
    public void testList() throws Exception {
        log.info(
                String.valueOf(mockMvc.perform(MockMvcRequestBuilders.get("/expense/list"))
                        .andReturn()
                        .getModelAndView()
                        .getModelMap()));
    }

//    @Test
//    public void testAdd() throws Exception {
//        String result = mockMvc.perform(MockMvcRequestBuilders.post("/expense/add")
//                .param("description", "테스트")
//                .param("amount", "1000")
//                .param("expenseDate", "2021-09-01")
//                .param("category", "1")
//                .param("paymentMethod", "newbie")
//                .param("location", "newbie")
//        ).andReturn().getResponse().getContentAsString();
//
//        log.info(result);
//    }

    @Test
    public void testAdd() throws Exception {
        String result = mockMvc.perform(MockMvcRequestBuilders.post("/expense/add")
                .param("description", "테스트")
                .param("amount", String.valueOf(1000))
                .param("expenseDate", LocalDate.parse("2021-09-01").toString())
                .param("category", String.valueOf(1))
                .param("paymentMethod", "newbie")
                .param("location", "newbie")
        ).andReturn().getResponse().getContentAsString();

        log.info(result);
    }

    //    @Test
//public void testGet() throws Exception {
//        log.info(String.valueOf(mockMvc.perform(MockMvcRequestBuilders
//                .get("/expense/get")
//                .param("id", "1"))
//                .andReturn()
//                .getModelAndView().getModelMap()));
//    }
    }


