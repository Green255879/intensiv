package bank;

import java.math.BigDecimal;

/**
 * Условие снятия при балансе >= запрашиваемой сумме
 */
public class DebitAccount extends BankAccount {

    public DebitAccount() {
    }

    public DebitAccount(long accountNumber, BigDecimal balance, String accountHolder) {
        super(accountNumber, balance, accountHolder);
    }

    /**
     * Метод для уменьшения баланса при выполнении условия
     * @param amount - сумма для снятия
     * @return снятая сумма
     */
    @Override
    public BigDecimal withdraw(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) < 0)
            return BigDecimal.ZERO;
        else if (balance.compareTo(amount) >= 0) {
            balance = balance.subtract(amount);
            return amount;
        }
        return BigDecimal.ZERO;
    }
}
