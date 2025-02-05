package bank;

import java.math.BigDecimal;

// Вычитание комиссии за снятие
public interface TransactionFee {
    BigDecimal applyFee(BigDecimal amount);
}
