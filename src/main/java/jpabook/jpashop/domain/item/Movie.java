package jpabook.jpashop.domain.item;

import lombok.Getter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("M")
@Getter
public class Movie extends Item {

    private String director;

    private String actor;

    Movie(String name, int price, int stockQuantity) {
        super(name, price, stockQuantity);
    }
}
