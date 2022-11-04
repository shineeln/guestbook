package kr.or.connect.guestbook.controller;

import kr.or.connect.guestbook.dto.Guestbook;
import kr.or.connect.guestbook.service.GuestbookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
public class GuestbookAnnotationController {
    @Autowired
    GuestbookService guestbookService;

    @GetMapping(path="/test/list")
    public String list(
            @RequestParam(name="start", required=false, defaultValue="0") int start,
            ModelMap model,
            @CookieValue(value="count", defaultValue = "1", required = true) String value,
            HttpServletResponse response
    ) {
        try {
            int i = Integer.parseInt(value);
            value = Integer.toString(++i);
        }catch(Exception ex) {
            value = "1";
        }

        Cookie cookie = new Cookie("count", value);
        cookie.setMaxAge(60*60*24*365);
        cookie.setPath("/");
        response.addCookie(cookie);

        List<Guestbook> list = guestbookService.getGuestbooks(start);

        int count = guestbookService.getCount();
        int pageCount = count / GuestbookService.LIMIT;
        if(count % GuestbookService.LIMIT > 0)
            pageCount++;

        // 페이지 수만큼 start의 값을 리스트로 저장
        // 예를 들면 페이지수가 3이면
        // 0, 5, 10 이렇게 저장된다.
        // list?start=0 , list?start=5, list?start=10 으로 링크가 걸린다.
        List<Integer> pageStartList = new ArrayList<>();
        for (int i = 0; i < pageCount; i++) {
            pageStartList.add(i* GuestbookService.LIMIT);
        }

        model.addAttribute("list", list);
        model.addAttribute("count", count);
        model.addAttribute("pageStartList", pageStartList);
        model.addAttribute("cookieCount", value);

        return "list";
    }

    @PostMapping(path="/test/write")
    public String write(@ModelAttribute Guestbook guestbook, HttpServletRequest request) {
        String clientIp = request.getRemoteAddr();
        System.out.println("clientIp" + clientIp);
        guestbookService.addGuestbook(guestbook,clientIp);
        return "redirect:list";
    }
}
