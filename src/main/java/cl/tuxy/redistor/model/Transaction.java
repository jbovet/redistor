package cl.tuxy.redistor.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@RedisHash("Transaction")
public class Transaction implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String referenceId;
    private Integer amount;
    private String invoiceNumber;
    private String purchaseOrder;


}
