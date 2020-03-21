package cn.edu.nwu.service;

import cn.edu.nwu.dao.AccountRepository;
import cn.edu.nwu.dao.LogRepository;
import cn.edu.nwu.dao.UserRepository;
import cn.edu.nwu.domain.Account;
import cn.edu.nwu.domain.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Service
@Transactional
public class AccountService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    LogRepository logRepository;

    private Lock lock=new ReentrantLock();

    public boolean  transfer(Log l1, Log l2) {
        Account account = accountRepository.findById(l1.getCard()).orElse(null);
        double newBalance;
        if (l2 == null) {
            if (l1.getType() == 0) {                                              //存钱操作
                newBalance = account.getAmount() + l1.getAmount();
                account.setAmount(newBalance);
                accountRepository.save(account);
            } else {                                                              //取钱操作
                newBalance = account.getAmount() - l1.getAmount();
                if (newBalance < 0)
                    return false;
                account.setAmount(newBalance);
                accountRepository.save(account);
            }
            logRepository.save(l1);                                             //写入日志
        }

        if (l1.getType() == 2) {
            lock.lock();
            double newBalance1 = account.getAmount() - l1.getAmount();
            if (newBalance1 < 0)
                return false;
            Account oppositeAccount = accountRepository.findById(l2.getCard()).orElse(null);
            double newBalance2 = oppositeAccount.getAmount() + l1.getAmount();

            account.setAmount(newBalance1);
            oppositeAccount.setAmount(newBalance2);
            accountRepository.save(account);
            accountRepository.save(oppositeAccount);

            logRepository.save(l1);
            logRepository.save(l2);
            lock.unlock();
        }
        return true;
    }

    public Page<Log> getLog(String card, int pageNum, int pageSize) {
        Log log = new Log();
        log.setCard(card);
        Example<Log> example = Example.of(log);
        PageRequest pageRequest = PageRequest.of(pageNum, pageSize, Sort.Direction.DESC, "time");
        Page<Log> all = logRepository.findAll(example, pageRequest);
        return all;
    }


    public double getAccount(String card) {
        Account account = accountRepository.findById(card).orElse(null);
        if(account!=null){
            return account.getAmount();
        }
        return 0.0;
    }

}
