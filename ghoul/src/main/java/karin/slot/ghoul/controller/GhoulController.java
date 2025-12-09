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
  public String redirectRoot(HttpSession session) {
    // redirect root to /ghoul
    // Attribute されてるものをクリアする
    session.removeAttribute("ghoulForm");
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
      return "redirect:/ghoul";
    }

    int delta = ghoulForm.isToggleFlag() ? -1 : 1;
    // int delta = 1;
    // if (ghoulForm.isToggleFlag() == true) {
    // delta = -1;
    // }
    if (action.equals("one")) {
      ghoulForm.setTotal1(ghoulForm.getTotal1() + delta);
    } else if (action.equals("two")) {
      ghoulForm.setTotal2(ghoulForm.getTotal2() + delta);
    } else if (action.equals("three")) {
      ghoulForm.setTotal3(ghoulForm.getTotal3() + delta);
    } else if (action.equals("four")) {
      ghoulForm.setTotal4(ghoulForm.getTotal4() + delta);
    } else if (action.equals("oneHit")) {
      ghoulForm.setCount1(ghoulForm.getCount1() + delta);
    } else if (action.equals("twoHit")) {
      ghoulForm.setCount2(ghoulForm.getCount2() + delta);
    } else if (action.equals("threeHit")) {
      ghoulForm.setCount3(ghoulForm.getCount3() + delta);
    } else if (action.equals("fourHit")) {
      ghoulForm.setCount4(ghoulForm.getCount4() + delta);
    } else if (action.equals("kk")) {
      ghoulForm.setKk(ghoulForm.getKk() + delta);
    } else if (action.equals("toggle")) {
      ghoulForm.setToggleFlag(!ghoulForm.isToggleFlag());
    }

    session.setAttribute("ghoulForm", ghoulForm);
    return "ghoul";
  }
}
