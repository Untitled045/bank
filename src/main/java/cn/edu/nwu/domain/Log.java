package cn.edu.nwu.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Entity
@Table(name = "log")
public class Log {
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    Date time;   // '操作时间',
    Double amount;  // '金额',
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id; // '自增主键'
    String oppsitecard;   // '对方卡号,如果为存取款则此字段无效',
    String card;  // '本人卡号',
    Integer type;// '记录类型,0存款1取款2转出3转入',

}
