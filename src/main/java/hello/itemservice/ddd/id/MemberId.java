package hello.itemservice.ddd.id;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class MemberId implements Serializable {

    @Column(name = "member_id")
    private String id;

    protected MemberId() {}
}
