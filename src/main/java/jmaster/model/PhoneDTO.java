package jmaster.model;

import jmaster.entity.User;
import lombok.Data;

@Data
public class PhoneDTO {
    private Long id;
    private String number;
    private User user;
}
