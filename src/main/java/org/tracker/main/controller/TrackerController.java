package org.tracker.main.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.tracker.common.domain.Type;
import org.tracker.common.service.NameIdService;
import org.tracker.main.domain.TrackerDTO;
import org.tracker.main.service.TrackerService;

@Controller
@Slf4j
@RequestMapping("/index")
@AllArgsConstructor
public class TrackerController {

    private final TrackerService service;
    private NameIdService nameIdService;

    @GetMapping
    public void index(Model model) {
        model.addAttribute("list", service.getList());
    }

//    @PostMapping("/add")
//    public String add(ExpenseDTO expense, RedirectAttributes rttr) {
//        log.info("addExpense: " + expense);
//        expenseService.add(expense);
//        rttr.addFlashAttribute("result", expense.getId());
//        return "redirect:/expense/list";
//    }

    @PostMapping("/add")
    public String add(@RequestBody TrackerDTO tracker, RedirectAttributes rttr) {
        String categoryName = tracker.getCategoryName();
        String assetName = tracker.getAssetName();
        String typeInKorean = tracker.getType();

        Long categoryId = nameIdService.findIdByName("CATEGORY", categoryName);
        Long assetId = nameIdService.findIdByName("ASSET", assetName);
        Type type = Type.fromKoreanName(typeInKorean);

        tracker.setCategoryId(categoryId);
        tracker.setAssetId(assetId);
        tracker.setType(type.name());

        service.add(tracker);

        rttr.addFlashAttribute("result", tracker.getId());
        return "redirect:/index/list";
    }

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

    @GetMapping("/get")
    public void get(@RequestParam("id") Long id, Model model) {
        log.info("/get");
        model.addAttribute("tracker", service.get(id));
    }

    @PostMapping("/modify")
    public String modify(TrackerDTO tracker, RedirectAttributes rttr) {
        log.info("updateTracker: " + tracker);
        if (service.modify(tracker)) {
            rttr.addFlashAttribute("result", "success");
        };
        return "redirect:/index/list";
    }

    @PostMapping("/remove")
    public String remove(@RequestParam("id") Long id, RedirectAttributes rttr) {
        log.info("deleteTracker: " + id);
        if (service.remove(id)) {
            rttr.addFlashAttribute("result", "success");
        };
        return "redirect:/index/list";
    }


}
