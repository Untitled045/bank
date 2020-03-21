package cn.edu.nwu.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Entity
@Table(name="account")
public class Account {
    @Id
    String card;
    Double amount;
}
