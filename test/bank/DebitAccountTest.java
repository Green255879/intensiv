package bank;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class DebitAccountTest {

    private BankAccount debitAccount;

    @BeforeEach
    void setUp() {
        debitAccount = new DebitAccount(12345L, BigDecimal.valueOf(3000), "Name");
    }

    /**
     * Снятие суммы меньше баланса
     */
    @Test
    void amountIsSmallerBalance() {
        BigDecimal amount = BigDecimal.valueOf(1000);
        BigDecimal withdraw = debitAccount.withdraw(amount);

        Assertions.assertEquals(BigDecimal.valueOf(1000), withdraw);
        Assertions.assertEquals(BigDecimal.valueOf(2000), debitAccount.getBalance());
    }

    /**
     * Снятие суммы, равной балансу
     */
    @Test
    void amountIsEqualBalance() {
        BigDecimal amount = BigDecimal.valueOf(3000);
        BigDecimal withdraw = debitAccount.withdraw(amount);

        Assertions.assertEquals(BigDecimal.valueOf(3000), withdraw);
        Assertions.assertEquals(BigDecimal.ZERO, debitAccount.getBalance());
    }

    /**
     * Снятие суммы больше баланса
     */
    @Test
    void amountIsBiggerBalance() {
        BigDecimal amount = BigDecimal.valueOf(6000);
        BigDecimal withdraw = debitAccount.withdraw(amount);

        Assertions.assertEquals(BigDecimal.ZERO, withdraw);
        Assertions.assertEquals(BigDecimal.valueOf(3000), debitAccount.getBalance());
    }

    /**
     * Снятие нулевой суммы
     */
    @Test
    void amountIsZero() {
        BigDecimal amount = BigDecimal.valueOf(0);
        BigDecimal withdraw = debitAccount.withdraw(amount);

        Assertions.assertEquals(BigDecimal.ZERO, withdraw);
        Assertions.assertEquals(BigDecimal.valueOf(3000), debitAccount.getBalance());
    }

    /**
     * Снятие отрицательной суммы
     */
    @Test
    void amountIsNegative() {
        BigDecimal amount = BigDecimal.valueOf(-1500);
        BigDecimal initialBalance = debitAccount.getBalance();
        BigDecimal withdraw = debitAccount.withdraw(amount);

        Assertions.assertEquals(BigDecimal.ZERO, withdraw);
        Assertions.assertEquals(initialBalance, debitAccount.getBalance());
    }

    /**
     * Снятие копейки (0.01 в рублях), проверка на снятие маленьких значений
     */
    @Test
    void withdrawKopeck() {
        BigDecimal amount = BigDecimal.valueOf(0.01);
        BigDecimal withdraw = debitAccount.withdraw(amount);

        Assertions.assertEquals(BigDecimal.valueOf(0.01), withdraw);
        Assertions.assertEquals(BigDecimal.valueOf(2999.99), debitAccount.getBalance());
    }

    /**
     * Снятие BigDecimal, проверка на снятие больших значений
     */
    @Test
    void withdrawBigDecimal() {
        debitAccount = new DebitAccount(12345L, BigDecimal.valueOf(1000000000000.00), "Name");
        BigDecimal amount = BigDecimal.valueOf(999999999999.99);
        BigDecimal withdraw = debitAccount.withdraw(amount);

        Assertions.assertEquals(BigDecimal.valueOf(999999999999.99), withdraw);
        Assertions.assertEquals(BigDecimal.valueOf(000000000000.01), debitAccount.getBalance());
    }

    /**
     * Снятие суммы с нулевого баланса
     */
    @Test
    void withdrawZeroBalance() {
        debitAccount = new DebitAccount(12345L, BigDecimal.ZERO, "Name");
        BigDecimal amount = BigDecimal.valueOf(100);
        BigDecimal withdraw = debitAccount.withdraw(amount);

        Assertions.assertEquals(BigDecimal.ZERO, withdraw);
        Assertions.assertEquals(BigDecimal.ZERO, debitAccount.getBalance());
    }
}