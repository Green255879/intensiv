package bank;

import java.math.BigDecimal;

public abstract class BankAccount {
    protected long accountNumber;
    protected BigDecimal balance;
    protected String accountHolder;

    public BankAccount() {
    }

    public BankAccount(long accountNumber, BigDecimal balance, String accountHolder) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.accountHolder = accountHolder;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public void setAccountHolder(String accountHolder) {
        this.accountHolder = accountHolder;
    }

    // Абстрактный метод для снятия средств
    public abstract BigDecimal withdraw(BigDecimal amount);

    // Абстрактный метод для пополнения счета
    public void deposit(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) > 0)
            balance = balance.add(amount);
    }
}