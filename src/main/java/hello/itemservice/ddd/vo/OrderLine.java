package hello.itemservice.ddd.vo;

import hello.itemservice.ddd.converter.MoneyConverter;
import hello.itemservice.ddd.id.ProductId;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
public class OrderLine {

    //@Embedded
    private ProductId productId;

    @Column(name = "price")
    @Convert(converter = MoneyConverter.class)
    private Money price;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "amounts")
    @Convert(converter = MoneyConverter.class)
    private Money amounts;

    protected OrderLine() {}
}
