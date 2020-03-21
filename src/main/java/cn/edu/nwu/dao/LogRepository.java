package cn.edu.nwu.dao;

import cn.edu.nwu.domain.Log;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LogRepository extends JpaRepository<Log,Integer> {
}
