package com.Controller;

import com.Common.Util;
import com.Service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping("/calculator")
    public String calculator(Model model) {
        model.addAttribute("result", 10000);
        return "calculator";
    }

    @PostMapping("/calculator")
    public String cal(Model model, @RequestParam(value = "number_1") int number1, @RequestParam(value = "number_2") int number2) {
        model.addAttribute("result", testService.calculator(number1, number2));
        return "calculator";
    }
}
