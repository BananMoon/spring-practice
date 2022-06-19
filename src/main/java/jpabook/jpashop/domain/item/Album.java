package jpabook.jpashop.domain.item;

import lombok.Getter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("A")
@Getter
public class Album extends Item {

    private String artist;

    private String etc;

    Album(String name, int price, int stockQuantity) {
        super(name, price, stockQuantity);
    }
}
