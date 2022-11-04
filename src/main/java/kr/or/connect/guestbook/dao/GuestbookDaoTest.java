package kr.or.connect.guestbook.dao;

import kr.or.connect.guestbook.config.ApplicationConfig;
import kr.or.connect.guestbook.dto.Guestbook;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Date;

public class GuestbookDaoTest {
    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(ApplicationConfig.class);

        GuestbookDao guestbookDao = ac.getBean(GuestbookDao.class);

        Guestbook guestbook = new Guestbook();
        guestbook.setName("shinee");
        guestbook.setContent("hello everyone");
        guestbook.setRegdate(new Date());
        Long id = guestbookDao.insert(guestbook);
        System.out.print("id:" + id);
    }
}
