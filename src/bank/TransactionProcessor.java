package bank;

import java.math.BigDecimal;
import java.util.List;

public class TransactionProcessor {
    public void processTransaction(List<BankAccount> accounts, BigDecimal amount) {
        for (BankAccount account : accounts) {
            System.out.println(account.withdraw(amount));
        }
    }
}
