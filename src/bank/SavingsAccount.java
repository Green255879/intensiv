package bank;

import java.math.BigDecimal;

/**
 * Ежемесячное начисление процентов
 */
public class SavingsAccount extends CreditAccount implements InterestBearing {
    private BigDecimal debt = BigDecimal.ZERO;

    private SavingsAccount(long accountNumber, BigDecimal balance, String accountHolder, BigDecimal creditLimit, BigDecimal debt) {
        super(accountNumber, balance, accountHolder, creditLimit);
        this.debt = debt;
    }

    /**
     * Метод начисления процентов
     */
    @Override
    public void applyInterest() {   // Если задействован кредит, то будем начислять проценты
        double interest = 0.06;
        BigDecimal penalty = BigDecimal.ZERO;
        if (balance.compareTo(BigDecimal.ZERO) < 0) {
            penalty = balance.multiply(BigDecimal.valueOf(interest));
            debt = debt.add(penalty);
        }
    }

    static SavingsAccountBuilder builder() {
        return new SavingsAccountBuilder();
    }

    static class SavingsAccountBuilder extends CreditAccountBuilder {
        private BigDecimal debt;

        public SavingsAccountBuilder accountNumber(long accountNumber) {
            this.accountNumber = accountNumber;
            return this;
        }

        public SavingsAccountBuilder balance(BigDecimal balance) {
            this.balance = balance;
            return this;
        }

        public SavingsAccountBuilder accountHolder(String accountHolder) {
            this.accountHolder = accountHolder;
            return this;
        }

        public SavingsAccountBuilder creditLimit(BigDecimal creditLimit) {
            this.creditLimit = creditLimit;
            return this;
        }

        public SavingsAccountBuilder debt(BigDecimal debt) {
            this.debt = debt;
            return this;
        }

        public SavingsAccount build() {
            return new SavingsAccount(accountNumber, balance, accountHolder, creditLimit, debt);
        }
    }
}
