package cn.edu.nwu.controller;


import cn.edu.nwu.domain.Log;
import cn.edu.nwu.domain.TransferVO;
import cn.edu.nwu.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


@RestController
@RequestMapping("/api/account" )
public class AccountController {
    @Autowired
    AccountService accountService;

    @PostMapping("/transfer")
    public boolean transfer(@RequestBody TransferVO transferVO){
        int type=transferVO.getType();
        double amount=transferVO.getAmount();
        String oppositecard=transferVO.getOppositecard();
        String card=transferVO.getCard();

        Date date = new Date();
        if(type==2){
            Log transferFrom = new Log(date,amount,null,oppositecard,card,2);
            Log transferTo = new Log(date,amount,null,card,oppositecard,3);
            return accountService.transfer(transferFrom,transferTo);
        }

        if(type==0||type==1){
            Log log = new Log(date,amount,null,null,card,type);
            return accountService.transfer(log,null);
        }
        return false;
    }

    @GetMapping("")
    public double getAccount(@RequestParam("card") String card){

        return accountService.getAccount(card);
    }
    @GetMapping("/list")
    public Page<Log> getLog(@RequestParam("card") String card, @RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize){

        return accountService.getLog(card,pageNum,pageSize);
    }


}
