package cn.edu.nwu.dao;

import cn.edu.nwu.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {
    User findByIdcard(String idcard);
}
