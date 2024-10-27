package aiia.airdemo.entity;

import aiia.airdemo.entity.enumeration.PostBlockType;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Embeddable
public class PostBlock {

    @Column(name = "block_order")
    private int order;

    @Enumerated(EnumType.STRING)
    private PostBlockType type;

    private String content;

}
