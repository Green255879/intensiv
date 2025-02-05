package bank;

import java.math.BigDecimal;

// Ежемесячное начисление процентов
public class SavingsAccount extends CreditAccount implements InterestBearing {
    public SavingsAccount(long accountNumber, BigDecimal balance, String accountHolder, BigDecimal creditLimit, BigDecimal debt) {
        super(accountNumber, balance, accountHolder, creditLimit);
        this.debt = debt;
    }

    public SavingsAccount(BigDecimal debt) {
        this.debt = debt;
    }

    private BigDecimal debt = BigDecimal.ZERO;

    // Метод начисления процентов
    @Override
    public void applyInterest() {   // Если задействован кредит, то будем начислять проценты
        double interest = 0.06;
        BigDecimal penalty = BigDecimal.ZERO;
        if (balance.compareTo(BigDecimal.ZERO) < 0) {
            penalty = balance.multiply(BigDecimal.valueOf(interest));
            debt = debt.add(penalty);
        }
    }
}
