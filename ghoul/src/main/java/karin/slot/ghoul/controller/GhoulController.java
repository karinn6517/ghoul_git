package karin.slot.ghoul.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import jakarta.servlet.http.HttpSession;
import karin.slot.ghoul.dto.GhoulForm;

@Controller
public class GhoulController {

  // path の mapping
  @GetMapping("/")
  public String redirectRoot() {
    // redirect root to /ghoul
    return "redirect:/ghoul";
  }

  @GetMapping("/ghoul")
  public String showGhoulForm(HttpSession session) {
    var ghoulForm = (GhoulForm) session.getAttribute("ghoulForm");
    if (ghoulForm == null) {
      session.setAttribute("ghoulForm", new GhoulForm());
    }
    return "ghoul";
  }

  @GetMapping("/ghoul/{action}")
  public String sessionSample(@PathVariable String action, HttpSession session) {
    var ghoulForm = (GhoulForm) session.getAttribute("ghoulForm");
    if (ghoulForm == null) {
      ghoulForm = new GhoulForm();
    } else {
      if (action.equals("one")) {
        ghoulForm.setTotal1(ghoulForm.getTotal1() + 1);
      } else if (action.equals("two")) {
        ghoulForm.setTotal2(ghoulForm.getTotal2() + 1);
      } else if (action.equals("three")) {
        ghoulForm.setTotal3(ghoulForm.getTotal3() + 1);
      } else if (action.equals("four")) {
        ghoulForm.setTotal4(ghoulForm.getTotal4() + 1);
      } else if (action.equals("oneHit")) {
        ghoulForm.setCount1(ghoulForm.getCount1() + 1);
      } else if (action.equals("twoHit")) {
        ghoulForm.setCount2(ghoulForm.getCount2() + 1);
      } else if (action.equals("threeHit")) {
        ghoulForm.setCount3(ghoulForm.getCount3() + 1);
      } else if (action.equals("fourHit")) {
        ghoulForm.setCount4(ghoulForm.getCount4() + 1);
      } else if (action.equals("kk")) {
        ghoulForm.setKk(ghoulForm.getKk() + 1);
      }
    }
    session.setAttribute("ghoulForm", ghoulForm);
    return "ghoul";
  }
}
