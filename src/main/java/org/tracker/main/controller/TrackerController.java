package org.tracker.main.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.tracker.common.convert.LocalDateToDate;
import org.tracker.common.convert.NameToId;
import org.tracker.main.domain.TrackerDTO;
import org.tracker.main.service.TrackerService;
import org.tracker.user.service.UserService;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/tracker")
public class TrackerController {

    private static final Logger logger = LogManager.getLogger(UserService.class);

    private final TrackerService service;
    private final UserService userService;

    private NameToId nameToId;

    public TrackerController(TrackerService service, UserService userService, NameToId nameToId) {
        this.service = service;
        this.userService = userService;
        this.nameToId = nameToId;
    }


    @GetMapping("/index")
    public String index(Model model) {
//        model.addAttribute("list", service.getList());
        return "tracker/index";
    }

//    @GetMapping
//    public String index(Model model, HttpSession session) {
//        UserDTO user = (UserDTO) session.getAttribute("user");
//        if (user != null) {
//            model.addAttribute("isLoggedIn", true);
//            model.addAttribute("list", service.getListById(user.getNum()));
//        } else {
//            model.addAttribute("isLoggedIn", false);
//        }
//        return "index";
//    }

    @PostMapping("/login")
    @ResponseBody
    public boolean login(@RequestParam String userId, @RequestParam String password, HttpSession session) {
        Long userNum = userService.login(userId, password);
        if (userNum != null) {
            session.setAttribute("userNum", userNum);
            return true;
        } else {
            return false;
        }
    }

//    @PostMapping("/login")
//    public boolean login(@RequestParam String userId, @RequestParam String password, HttpSession session) {
//        Long userNum = userService.login(userId, password);
//        logger.debug("userNum: " + userNum);
//        if (userNum != null) {
//            session.setAttribute("userNum", userNum);
//            return "redirect:/tracker/list";
//        } else {
//            return "/tracker/login";
//        }
//    }

    @GetMapping("/list")
    public String getListById(Model model, HttpSession session, @RequestParam(required = false) Long typeId) {
        Long userNum = (Long) session.getAttribute("userNum");
        if (userNum != null) {
            populateModelWithTrackerData(model, userNum, typeId);
        }
        return "tracker/list";
    }

//    @GetMapping("/list")
//    public String getListById(Model model, HttpSession session) {
//        Long userNum = (Long) session.getAttribute("userNum");
//        if (userNum != null) {
//            List<TrackerDTO> trackerList = service.getListById(userNum);
//            trackerList.forEach(tracker -> {
//                Date date = LocalDateToDate.convert(tracker.getTrackerDate());
//                tracker.setTrackerDate(date);
//            });
//            model.addAttribute("trackerList", trackerList);
//            populateModelWithTrackerData(model, userNum);
//        }
//        return "tracker/list";
//    }

    private void populateModelWithTrackerData(Model model, Long userNum, Long typeId) {
        model.addAttribute("trackerList", service.getListById(userNum, typeId));
        model.addAttribute("categoryList", service.findCategory(userNum, typeId));
        model.addAttribute("assetList", service.findAsset(userNum, typeId));
        model.addAttribute("countList", service.countList(userNum));
    }

//    @PostMapping("/add")
//    public String add(ExpenseDTO expense, RedirectAttributes rttr) {
//        log.info("addExpense: " + expense);
//        expenseService.add(expense);
//        rttr.addFlashAttribute("result", expense.getId());
//        return "redirect:/expense/list";
//    }

    @PostMapping("/add")
    public String add(@RequestBody TrackerDTO tracker, RedirectAttributes rttr, HttpSession session) {
        String categoryName = tracker.getCategoryName();
        String assetName = tracker.getAssetName();
        String TypeName = tracker.getTypeName();

        Long categoryId = nameToId.findIdByName("CATEGORY", categoryName);
        Long assetId = nameToId.findIdByName("ASSET", assetName);
        Long TypeId = nameToId.findIdByName("TYPE", TypeName);
        Long userNum = (Long) session.getAttribute("userNum");

        tracker.setCategoryId(categoryId);
        tracker.setAssetId(assetId);
        tracker.setTypeId(TypeId);
        tracker.setUserId(userNum);

        service.add(tracker);

        rttr.addFlashAttribute("result", tracker.getId());
        return "redirect:/tracker/list";
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
        return "redirect:/tracker/list";
    }

    @PostMapping("/remove")
    public String remove(@RequestParam("id") Long id, RedirectAttributes rttr) {
        log.info("deleteTracker: " + id);
        if (service.remove(id)) {
            rttr.addFlashAttribute("result", "success");
        };
        return "redirect:/tracker/list";
    }
}
