package org.tracker.expense.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.tracker.common.domain.TrackerDTO;
import org.tracker.expense.domain.ExpenseDTO;
import org.tracker.expense.service.ExpenseService;

@Controller
@Slf4j
@RequestMapping("/expense")
@AllArgsConstructor
public class ExpenseController {

    private ExpenseService expenseService;
    private TrackerDTO trackerDTO;

    @PostMapping("/add")
    public String add(ExpenseDTO expense, RedirectAttributes rttr) {
        log.info("addExpense: " + expense);
        expenseService.add(expense);
        rttr.addFlashAttribute("result", expense.getId());
        return "redirect:/expense/list";
    }

//    @PostMapping("/add")
//    public String add(@RequestBody TrackerDTO tracker, RedirectAttributes rttr) {
//        // Get the category name from the request
//        String categoryName = tracker.getCategoryName();
//
//        // Query the CATEGORY_EX table to find the ID of the category
//        Long categoryId = categoryExService.findIdByName(categoryName);
//
//        // Set the category ID in the expense object
//        expense.setCategoryId(categoryId);
//
//        // Save the expense object to the database
//        expenseService.add(expense);
//
//        rttr.addFlashAttribute("result", expense.getId());
//        return "redirect:/expense/list";
//    }

//    @GetMapping("/list")
//    public ModelAndView list(Model addModel) {
//        ModelAndView mav = new ModelAndView();
//        mav.setViewName("/expense/list");
//        mav.addObject("list", service.searchList());
//
//        log.info("list");
//
//        return mav;
//    }

    @GetMapping("/list")
    public void list(Model model) {
        log.info("list");
        model.addAttribute("list", expenseService.searchList());
    }

    @GetMapping("/get")
    public void get(@RequestParam("id") Long id, Model model) {
        log.info("/get");
        model.addAttribute("expense", expenseService.get(id));
    }

    @PostMapping("/modify")
    public String modify(ExpenseDTO expense, RedirectAttributes rttr) {
        log.info("updateExpense: " + expense);
        if (expenseService.modify(expense)) {
            rttr.addFlashAttribute("result", "success");
        };
        return "redirect:/expense/list";
    }

    @PostMapping("/remove")
    public String remove(@RequestParam("id") Long id, RedirectAttributes rttr) {
        log.info("deleteExpense: " + id);
        if (expenseService.remove(id)) {
            rttr.addFlashAttribute("result", "success");
        };
        return "redirect:/expense/list";
    }
}
