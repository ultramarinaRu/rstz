package test;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MortgageController {

    @Value("${test.minsum}")
    int minsum;
    @Value("${test.maxsum}")
    int maxsum;
    @Value("${test.minmonths}")
    int minmonths;
    @Value("${test.maxmonths}")
    int maxmonths;
    @Value("${test.percent}")
    double percent;

    @GetMapping("/mortgage")
    public String form(Model model) {
        model.addAttribute("params", new Params(minsum, minmonths, percent));
        model.addAttribute("error", "");
        return "params";
    }

    private String checkParams(Params p) {
        int temp = p.getSum();
        if (temp < minsum || temp > maxsum) return "Сумма должна быть от " + minsum + " до " + maxsum;
        temp = p.getMonths();
        if (temp < minmonths || temp > maxmonths) return "Месяцев должно быть от " + minmonths + " до " + maxmonths;
        return "";
    }

    @PostMapping("/mortgage")
    public String submit(@ModelAttribute Params params, ModelMap model) {
        try {
            if (checkParams(params).isEmpty()) {
                model.addAttribute("params", params);
                model.addAttribute("list", new Calculation(params).execute());
                return "result";
            } else {
                model.addAttribute("error", checkParams(params));
                return "params";
            }
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "result";
        }
    }

}
