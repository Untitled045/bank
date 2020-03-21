package cn.edu.nwu.controller;

import cn.edu.nwu.dao.NoticeRepository;
import cn.edu.nwu.domain.Notice;
import cn.edu.nwu.domain.NoticeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notice")
public class NoticeController {

    @Autowired
    NoticeRepository noticeRepository;
    @GetMapping("")
    public String getNotice(){
        Notice notice = noticeRepository.findById(1).orElse(null);
        if(notice!=null){
            return notice.getContent();
        }
        return null;
    }

    @PostMapping("")
    public boolean modifyNotice(@RequestBody NoticeVO noticeVO){
        try{
            Notice notice = new Notice(1, noticeVO.getContent());
            noticeRepository.save(notice);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }
}
