package hello.itemservice.ddd.entity;

import hello.itemservice.ddd.converter.MoneyConverter;
import hello.itemservice.ddd.id.OrderNo;
import hello.itemservice.ddd.vo.Money;
import hello.itemservice.ddd.vo.OrderLine;
import hello.itemservice.ddd.vo.Orderer;
import hello.itemservice.ddd.vo.ShippingInfo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "purchase_order")
public class Orders {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_no")
    @EmbeddedId
    private OrderNo id;

    @Embedded
    private Orderer orderer;

    @Embedded
    private ShippingInfo shippingInfo;

    @Column(name = "total_amounts")
    @Convert(converter = MoneyConverter.class)
    private Money totalAmounts;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "order_line",
                joinColumns = @JoinColumn(name = "order_number"))
    @OrderColumn(name = "line_idx")
    private List<OrderLine> orderLines = new ArrayList<>();

    protected Orders() {}

}
