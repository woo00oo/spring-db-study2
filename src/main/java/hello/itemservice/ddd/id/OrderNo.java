package hello.itemservice.ddd.id;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class OrderNo implements Serializable {

    private Long orderNo;

}
