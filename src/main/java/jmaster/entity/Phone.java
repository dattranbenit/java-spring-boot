package jmaster.entity;

import java.io.Serializable;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@Table(name = "phone")
@NoArgsConstructor
@RequiredArgsConstructor
public class Phone implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull
    private Long id;

    @Column(name = "number")
    private String number;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
