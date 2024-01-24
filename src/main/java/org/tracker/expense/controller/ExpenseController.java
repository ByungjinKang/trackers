package org.tracker.expense.controller;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.tracker.expense.domain.ExpenseDTO;
import org.tracker.expense.service.ExpenseService;

@RestController
@Slf4j
@RequestMapping("/expense")
@AllArgsConstructor
public class ExpenseController {

    private ExpenseService service;

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @PostMapping("/add")
    public String add(ExpenseDTO expense, RedirectAttributes rttr) {
        log.info("addExpense: " + expense);
        service.add(expense);
        rttr.addFlashAttribute("result", expense.getId());
        return "redirect:/expense/list";
    }

    @GetMapping("/list")
    public void list(Model model) {
        log.info("list");
        model.addAttribute("list", service.searchList());
    }

    @GetMapping("/get")
    public void get(@RequestParam("id") Long id, Model model) {
        log.info("/get");
        model.addAttribute("expense", service.get(id));
    }

    @PostMapping("/modify")
    public String modify(ExpenseDTO expense, RedirectAttributes rttr) {
        log.info("updateExpense: " + expense);
        if (service.modify(expense)) {
            rttr.addFlashAttribute("result", "success");
        };
        return "redirect:/expense";
    }

    @PostMapping("/remove")
    public String remove(@RequestParam("id") Long id, RedirectAttributes rttr) {
        log.info("deleteExpense: " + id);
        if (service.remove(id)) {
            rttr.addFlashAttribute("result", "success");
        };
        return "redirect:/expense";
    }
}
