package bank;

import java.math.BigDecimal;

// Условие снятия при балансе большим запрашиваемой суммы
public class DebitAccount extends BankAccount {

    public DebitAccount() {
    }

    public DebitAccount(long accountNumber, BigDecimal balance, String accountHolder) {
        super(accountNumber, balance, accountHolder);
    }

    // Метод для уменьшения баланса при выполнении условия
    @Override
    public BigDecimal withdraw(BigDecimal amount) {
        if (balance.compareTo(amount) >= 0) {
            balance = balance.subtract(amount);
            return amount;
        }
        return BigDecimal.ZERO;
    }
}
