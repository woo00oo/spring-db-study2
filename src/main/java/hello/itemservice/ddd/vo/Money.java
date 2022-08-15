package hello.itemservice.ddd.vo;

import lombok.Getter;

@Getter
public class Money {

    private Integer value;

    protected Money() {}

    public Money(Integer value) {
        this.value = value;
    }

}
