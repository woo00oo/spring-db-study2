package hello.itemservice.ddd.vo;

import javax.persistence.*;

@Embeddable
public class ShippingInfo {

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "zipCode",
                        column = @Column(name = "shipping_zipcode")),
            @AttributeOverride(name = "address1",
                        column = @Column(name = "shipping_addr1")),
            @AttributeOverride(name = "address2",
                        column = @Column(name = "shipping_addr2"))
    })
    private Address address;

    @Column(name = "shipping_message")
    private String message;

    @Embedded
    private Receiver receiver;

    protected ShippingInfo() {}

}
