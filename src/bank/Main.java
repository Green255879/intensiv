package bank;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        BankAccount debitAccount = new DebitAccount(1, BigDecimal.valueOf(100), "Test");
        BankAccount creditAccount = new CreditAccount(2, BigDecimal.valueOf(1500), "Test2", BigDecimal.valueOf(-5000));
        BankAccount savingAccount = new SavingsAccount(3, BigDecimal.valueOf(2000), "Test3", BigDecimal.valueOf(-5000), BigDecimal.valueOf(0));

        List<BankAccount> accounts = new ArrayList<>();
        accounts.add(debitAccount);
        accounts.add(creditAccount);
        accounts.add(savingAccount);

        TransactionProcessor processor = new TransactionProcessor();

        System.out.println("Снятие на сумму 300:");
        processor.processTransaction(accounts, BigDecimal.valueOf(300));

        System.out.println("Снятие на сумму 500:");
        processor.processTransaction(accounts, BigDecimal.valueOf(500));
    }
}