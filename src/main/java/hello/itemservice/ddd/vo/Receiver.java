package hello.itemservice.ddd.vo;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Receiver {

    @Column(name = "receiver_name")
    private String name;

    @Column(name = "receiver_phone")
    private String phone;

    protected Receiver() {}
}
