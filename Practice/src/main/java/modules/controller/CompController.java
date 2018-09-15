package modules.controller;

import modules.service.CompService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CompController {

    @Autowired
    public CompService service;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView main() {
        return new ModelAndView("indexComp", "listComps", service.getCompList());
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteComp(@PathVariable("id") int id) {
        service.deleteComp(id);
        return "redirect:/";
    }
}
