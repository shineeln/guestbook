package kr.or.connect.guestbook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class GuessNumberController {
    @GetMapping("/guess")
    public String guess(@RequestParam(name="number", required=false) Integer number, HttpSession session, ModelMap model) {
        String message = null;

        if(number == null) {
            session.setAttribute("count", 0);
            session.setAttribute("randomNumber", (int)(Math.random() * 100) + 1);
            message = "Minii bodson toog taagaarai!";
        } else {
            int count = (Integer)session.getAttribute("count");
            int randomNumber = (Integer)session.getAttribute("randomNumber");

            if(number < randomNumber) {
                message = "oruulsan too chn minii bodson toonoos baga baina";
                session.setAttribute("count", ++count);
            } else if(number > randomNumber) {
                message = "oruulsan too chn minii bodsonoos ih bna";
                session.setAttribute("count", ++count);
            } else {
                message = "OK" + ++count + "udaa taagaad onoloo. Minii bodsn too bol" + number;
                session.removeAttribute("count");
                session.removeAttribute("randomNumber");
            }
        }

        model.addAttribute("message", message);

        return "guess";
    }
}
