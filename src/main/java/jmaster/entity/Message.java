package jmaster.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@Table(name = "message")
@NoArgsConstructor
@RequiredArgsConstructor
public class Message implements Serializable {
    private static final long serialVersionUID = 3947385018982715905L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull
    private Long id;

    //khong duoc de ten truong la from do from la Reserved PostSQL word, phai de ten khac
    @Column(name = "from_account")
    private String from;

    @Column(name = "to_account")
    private String to;

    @Column(name = "receiver")
    private String receiver;

    @Column(name = "subject")
    private String subject;

    @Column(name = "content")
    private String content;
}
