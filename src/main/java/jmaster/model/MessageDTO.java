package jmaster.model;

import lombok.Data;

@Data
public class MessageDTO {
    private String from;
    private String to;
    private String receiver;
    private String subject;
    private String content;
}
