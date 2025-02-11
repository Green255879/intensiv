package bank;

import java.math.BigDecimal;

/**
 * Разрешает уходить в минус до лимита
 */
public class CreditAccount extends BankAccount implements TransactionFee {
    private final BigDecimal creditLimit;

    public BigDecimal getCreditLimit() {
        return creditLimit;
    }

    protected CreditAccount(long accountNumber, BigDecimal balance, String accountHolder, BigDecimal creditLimit) {
        super(accountNumber, balance, accountHolder);
        this.creditLimit = creditLimit;
    }

    private CreditAccount() {
        super();
        this.creditLimit = BigDecimal.valueOf(-5000);
    }

    /**
     * Метод для снятия средств со счета
     *
     * @param amount - сумма для снятия
     * @return снятая сумма
     */
    @Override
    public BigDecimal withdraw(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) < 0)
            return BigDecimal.ZERO;
        BigDecimal commission = applyFee(amount);
        BigDecimal amountPlusCommission = amount.add(commission);
        BigDecimal subtract = balance.subtract(amountPlusCommission);
        if (subtract.compareTo(creditLimit) >= 0) {
            balance = subtract;
            return amount;
        }
        return BigDecimal.ZERO;
    }

    /**
     * Интерфейс для списания комиссии c баланса
     *
     * @param amount - сумма для снятия
     * @return коммиссия
     */
    @Override
    public BigDecimal applyFee(BigDecimal amount) {
        BigDecimal commission = BigDecimal.valueOf(0.01);
        commission = commission.multiply(amount);
        return commission;
    }

    static CreditAccountBuilder builder() {
        return new CreditAccountBuilder();
    }

    static class CreditAccountBuilder {
        protected BigDecimal creditLimit;
        protected long accountNumber;
        protected BigDecimal balance;
        protected String accountHolder;

        public CreditAccountBuilder creditLimit(BigDecimal creditLimit) {
            this.creditLimit = creditLimit;
            return this;
        }

        public CreditAccountBuilder accountNumber(long accountNumber) {
            this.accountNumber = accountNumber;
            return this;
        }

        public CreditAccountBuilder balance(BigDecimal balance) {
            this.balance = balance;
            return this;
        }

        public CreditAccountBuilder accountHolder(String accountHolder) {
            this.accountHolder = accountHolder;
            return this;
        }

        public CreditAccount build() {
            if (this.creditLimit == null)
                this.creditLimit = BigDecimal.valueOf(-5000);
            return new CreditAccount(accountNumber, balance, accountHolder, creditLimit);
        }
    }

}
