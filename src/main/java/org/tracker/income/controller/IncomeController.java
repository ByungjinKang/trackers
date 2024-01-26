package org.tracker.income.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.tracker.income.domain.IncomeDTO;
import org.tracker.income.service.IncomeService;

@Controller
@Slf4j
@RequestMapping("/income")
@AllArgsConstructor
public class IncomeController {

    private IncomeService service;

    @PostMapping("/add")
    public String add(IncomeDTO expense, RedirectAttributes rttr) {
        log.info("addExpense: " + expense);
        service.add(expense);
        rttr.addFlashAttribute("result", expense.getId());
        return "redirect:/income/list";
    }

    @GetMapping("/list")
    public void list(Model model) {
        log.info("list");
        model.addAttribute("list", service.searchList());
    }

    @GetMapping("/get")
    public void get(@RequestParam("id") Long id, Model model) {
        log.info("/get");
        model.addAttribute("income", service.get(id));
    }

    @PostMapping("/modify")
    public String modify(IncomeDTO expense, RedirectAttributes rttr) {
        log.info("updateExpense: " + expense);
        if (service.modify(expense)) {
            rttr.addFlashAttribute("result", "success");
        };
        return "redirect:/income/list";
    }

    @PostMapping("/remove")
    public String remove(@RequestParam("id") Long id, RedirectAttributes rttr) {
        log.info("deleteExpense: " + id);
        if (service.remove(id)) {
            rttr.addFlashAttribute("result", "success");
        };
        return "redirect:/income/list";
    }
}
