package jmaster.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "product")
@NoArgsConstructor
@RequiredArgsConstructor
public class Product implements Serializable {

    private static final long serialVersionUID = 8006931992002204028L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Long price;
}
