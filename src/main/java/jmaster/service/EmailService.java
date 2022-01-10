package jmaster.service;

import jmaster.dao.EmailDao;
import jmaster.entity.Message;
import org.modelmapper.ModelMapper;
import jmaster.model.MessageDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;
import java.nio.charset.StandardCharsets;

public interface EmailService {
    void sendEmail(MessageDTO messageDTO);
}

@Transactional
@Service
class EmailServiceImpl implements EmailService {
    private static final Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

    @Autowired
    private EmailDao emailDao;

    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private SpringTemplateEngine templateEngine;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public void sendEmail(MessageDTO messageDTO) {
        try {
            logger.warn("Start send email");
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, StandardCharsets.UTF_8.name());

            Context context = new org.thymeleaf.context.Context();
            context.setVariable("receiver", messageDTO.getReceiver());
            context.setVariable("content", messageDTO.getContent());
            String html = templateEngine.process("user/email", context);

            helper.setTo(messageDTO.getTo());
            helper.setText(html, true);
            helper.setSubject(messageDTO.getSubject());
            helper.setFrom(messageDTO.getFrom());
            javaMailSender.send(message);

            logger.warn("END");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        Message message = modelMapper.map(messageDTO, Message.class);
        emailDao.add(message);
    }

}
