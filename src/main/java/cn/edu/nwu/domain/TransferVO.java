package cn.edu.nwu.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransferVO {
     Integer type;
    Double amount;
   String oppositecard;
   String card;
}
