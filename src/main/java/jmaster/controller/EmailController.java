package jmaster.controller;

import jmaster.model.MessageDTO;
import jmaster.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {
    @Autowired
    private EmailService emailService;

    @PostMapping("/email")
    public void sendEmail(@RequestBody MessageDTO messageDTO) {
        emailService.sendEmail(messageDTO);
    }
}
