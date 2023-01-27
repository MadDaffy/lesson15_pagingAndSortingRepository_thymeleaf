package com.geekbrains.demoboot.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

//TODO: аннотацию data лучше не использовать в связке с jpa, могут быть не явные проблемы и конфликты. + нужно еще на счет ключа первичного учитывать
//TODO: https://habr.com/ru/company/haulmont/blog/564682/
//TODO: https://thorben-janssen.com/lombok-hibernate-how-to-avoid-common-pitfalls/
//TODO: https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
//TODO: таблица должна называться не во множественном числе. Это представление единичной сущености
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "price")
    private int price;
}
