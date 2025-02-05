package bank;

import java.math.BigDecimal;

// Разрешает уходить в минус до лимита
public class CreditAccount extends BankAccount implements TransactionFee {
    private final BigDecimal creditLimit;

    public CreditAccount(long accountNumber, BigDecimal balance, String accountHolder, BigDecimal creditLimit) {
        super(accountNumber, balance, accountHolder);
        this.creditLimit = creditLimit;
    }

    public CreditAccount() {
        super();
        this.creditLimit = BigDecimal.valueOf(-5000);
    }

    // Метод для снятия средств со счета
    @Override
    public BigDecimal withdraw(BigDecimal amount) {
        BigDecimal commission = applyFee(amount);
        BigDecimal amountPlusCommission = amount.add(commission);
        BigDecimal subtract = balance.subtract(amountPlusCommission);
        if (subtract.compareTo(creditLimit) >= 0) {
            balance = subtract;
            return amount;
        }
        return BigDecimal.ZERO;
    }

    // Интерфейс для списания комиссии c баланса
    @Override
    public BigDecimal applyFee(BigDecimal amount) {
        BigDecimal commission = BigDecimal.valueOf(0.01);
        commission = commission.multiply(amount);
        return commission;
    }
}
