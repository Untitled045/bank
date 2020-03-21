package cn.edu.nwu.dao;

import cn.edu.nwu.domain.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice,Integer> {
}
