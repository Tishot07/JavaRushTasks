package modules.controller;

import modules.comp.Comp;
import modules.service.CompService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CompController {

    @Autowired
    private CompService service;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView main() {
        return new ModelAndView("indexComp", "listComps", service.getCompList());
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteComp(@PathVariable("id") int id) {
        service.deleteComp(id);
        return "redirect:/";
    }

    @GetMapping("/addComp")
    public String createCompPage(Model model) {
        model.addAttribute(new Comp());
        return "createComp";
    }

    //@RequestMapping(value = "/addComp", method = RequestMethod.POST)
    @PostMapping("/addComp")
    public String addComp(@ModelAttribute("comp") Comp newComp){
        service.addComp(newComp);
        //model.addAttribute("comp", newComp);
        return "redirect:/";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String updateComp(@PathVariable("id") int id, Model model) {
        model.addAttribute("comp", service.getById(id));
        return "editComp";
    }


    @PostMapping("/update/update")
    public String updateComp(@ModelAttribute("comp") Comp newComp) {
        System.out.println("I am post update");
        service.update(newComp);
        return "redirect:/";
    }

}
