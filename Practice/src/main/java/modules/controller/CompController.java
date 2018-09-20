package modules.controller;

import modules.entity.EntityComp;
import modules.service.CompServiceInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CompController {

    @Autowired
    private CompServiceInt service;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView main() {
        System.out.println("root");
        for (EntityComp c:
                service.getCompList()) {
            System.out.println("?");
            System.out.println(c);
        }
        return new ModelAndView("indexComp", "listComps", service.getCompList());
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteComp(@PathVariable("id") int id) {
        service.deleteComp(id);
        return "redirect:/";
    }

    @RequestMapping(value = "/addComp", method = RequestMethod.GET)
    public String createCompPage(Model model) {
        model.addAttribute("addcomp", new EntityComp());
        return "createComp";
    }

    @RequestMapping(value = "/addComp", method = RequestMethod.POST)
    public String addComp(@ModelAttribute("addcomp") EntityComp newComp){
        service.addComp(newComp);
        return "redirect:/";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String updateComp(@PathVariable("id") int id, Model model) {
        model.addAttribute("comp", service.getById(id));
        return "editComp";
    }


    //@PostMapping("/update/update")
    @RequestMapping(value = "/update/update", method = RequestMethod.POST)
    public String updateComp(@ModelAttribute("comp") EntityComp newComp) {
        service.update(newComp);
        return "redirect:/";
    }
}
