package kr.or.connect.guestbook.service;

import kr.or.connect.guestbook.dto.Guestbook;

import java.util.List;

public interface GuestbookService {
    public static final Integer LIMIT = 5;
    public List<Guestbook> getGuestbooks(Integer start);
    public int deleteGuestbook(Long id, String ip);
    public Guestbook addGuestbook(Guestbook questbook, String ip);
    public int getCount();
}
