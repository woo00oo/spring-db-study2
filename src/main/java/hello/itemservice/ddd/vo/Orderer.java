package hello.itemservice.ddd.vo;

import hello.itemservice.ddd.id.MemberId;

import javax.persistence.*;

@Embeddable
public class Orderer {

    @Embedded
    @AttributeOverrides(
            @AttributeOverride(name = "id", column = @Column(name = "orderer_id"))
    )
    private MemberId memberId;

    @Column(name = "orderer_name")
    private String name;

    protected Orderer() {}
}
