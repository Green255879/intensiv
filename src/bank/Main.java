package bank;

import java.math.BigDecimal;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        BankAccount debitAccount = new DebitAccount(1, BigDecimal.valueOf(100), "Test");

        BankAccount creditAccount = CreditAccount.builder()
                .accountNumber(2L)
                .balance(BigDecimal.valueOf(1500))
                .accountHolder("Test2")
                .creditLimit(BigDecimal.valueOf(-5000))
                .build();

        BankAccount savingAccount = SavingsAccount.builder()
                .accountNumber(3L)
                .balance(BigDecimal.valueOf(2000))
                .accountHolder("Test3")
                .creditLimit(BigDecimal.valueOf(-5000))
                .debt(BigDecimal.valueOf(0))
                .build();

        List<BankAccount> accounts = List.of(debitAccount, creditAccount, savingAccount);

        TransactionProcessor processor = new TransactionProcessor();

        System.out.println("Снятие на сумму 300:");
        processor.processTransaction(accounts, BigDecimal.valueOf(300));

        System.out.println("Снятие на сумму 500:");
        processor.processTransaction(accounts, BigDecimal.valueOf(500));

        CreditAccount.builder()
                .creditLimit(BigDecimal.ZERO)
                .build();
    }
}