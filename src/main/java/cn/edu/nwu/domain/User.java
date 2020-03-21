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
@Table(name="user")
public class User {
    private String username;
    private String password;
    private String gender;
    private String tel;
    private String idcard;
    private String address;
    private Integer admin;
    private Integer state;
    @Id
    private String card;

    public User(String card,String pass){
        this.card=card;
        this.password=pass;
    }

}