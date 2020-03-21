package cn.edu.nwu.dao;

import cn.edu.nwu.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,String> {
}
