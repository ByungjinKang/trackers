package org.tracker.common.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.tracker.common.service.TrackerService;

@Controller
@Slf4j
@RequestMapping("/index")
@AllArgsConstructor
public class TrackerController {

    private final TrackerService service;

    @GetMapping
    public void index(Model model) {
        model.addAttribute("list", service.getAllListByDate());
    }


}
