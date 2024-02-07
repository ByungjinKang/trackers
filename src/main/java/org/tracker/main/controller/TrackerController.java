package org.tracker.main.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "tracker/index";
    }

//    @GetMapping("/list")
//    public String getListById(Model model, HttpSession session, @RequestParam(required = false) Long typeId) {
//        Long userNum = (Long) session.getAttribute("userNum");
//        if (userNum != null) {
//            populateModelWithTrackerData(model, userNum, typeId);
//        }
//        return "tracker/list";
//    }

//    @GetMapping("/list")
//    public String getListByDate(Model model, HttpSession session, @RequestParam(required = false) Long typeId, @RequestParam(required = false) Integer year, @RequestParam(required = false) Integer month) {
//        Long userNum = (Long) session.getAttribute("userNum");
//        if (userNum != null) {
//            if (year != null && month != null) {
//                // 년도와 월이 주어진 경우, 해당 년도와 월의 트래커 목록을 가져옵니다.
//                model.addAttribute("trackerList", service.getListByDate(userNum, typeId, year, month));
//            } else {
//                // 년도와 월이 주어지지 않은 경우, 사용자 ID를 기반으로 트래커 목록을 가져옵니다.
//                model.addAttribute("trackerList", service.getListById(userNum, typeId));
//            }
//            populateModelWithTrackerData(model, userNum, typeId);
//        }
//        return "tracker/list";
//    }

    @GetMapping("/list")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getListByDate(HttpSession session, @RequestParam(required = false) Long typeId, @RequestParam(required = false) Integer year, @RequestParam(required = false) Integer month) {
        Long userNum = (Long) session.getAttribute("userNum");
        List<TrackerDTO> trackerList = null;
        Map<String, Object> response = new HashMap<>();
        if (userNum != null) {
            if (year != null && month != null) {
                trackerList = service.getListByDate(userNum, typeId, year, month);
            } else {
                trackerList = service.getListById(userNum, typeId);
            }
            response.put("trackerList", trackerList);
            response.put("categoryListExpense", service.findCategoryExpense(userNum));
            response.put("categoryListIncome", service.findCategoryIncome(userNum));
            response.put("assetListExpense", service.findAssetExpense(userNum));
            response.put("assetListIncome", service.findAssetIncome(userNum));
            response.put("countList", service.countList(userNum));
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/view")
    public String viewTrackerList(Model model, HttpSession session, @RequestParam(required = false) Long typeId, @RequestParam(required = false) Integer year, @RequestParam(required = false) Integer month) {
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
        model.addAttribute("categoryListExpense", service.findCategoryExpense(userNum));
        model.addAttribute("categoryListIncome", service.findCategoryIncome(userNum));
        model.addAttribute("assetListExpense", service.findAssetExpense(userNum));
        model.addAttribute("assetListIncome", service.findAssetIncome(userNum));
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
