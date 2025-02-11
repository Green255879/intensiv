package bank;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class CreditAccountTest {

    private BankAccount creditAccount;

    @BeforeEach
    void setUp() {
        creditAccount = CreditAccount.builder()
                .accountNumber(12345L)
                .balance(BigDecimal.valueOf(3000))
                .accountHolder("Name")
                .creditLimit(BigDecimal.valueOf(-5000))
                .build();
    }

    /**
     * Снятие суммы меньше баланса
     */
    @Test
    void amountIsSmallerBalance() {
        BigDecimal amount = BigDecimal.valueOf(1000);
        BigDecimal withdraw = creditAccount.withdraw(amount);

        Assertions.assertEquals(BigDecimal.valueOf(1000), withdraw);
        Assertions.assertEquals(1990, creditAccount.getBalance().intValue());
    }

    /**
     * Снятие суммы, равной балансу
     */
    @Test
    void amountIsEqualBalance() {
        BigDecimal amount = BigDecimal.valueOf(3000);
        BigDecimal withdraw = creditAccount.withdraw(amount);

        Assertions.assertEquals(BigDecimal.valueOf(3000), withdraw);
        Assertions.assertEquals(-30, creditAccount.getBalance().intValue());
    }

    /**
     * Снятие суммы, превышающей баланс, но в пределах кредита
     */
    @Test
    void amountIsBiggerBalanceOnCredit() {
        BigDecimal amount = BigDecimal.valueOf(6000);
        BigDecimal withdraw = creditAccount.withdraw(amount);

        Assertions.assertEquals(BigDecimal.valueOf(6000), withdraw);
        Assertions.assertEquals(-3060, creditAccount.getBalance().intValue());
    }

    /**
     * Снятие суммы, превышающей кредит
     */
    @Test
    void amountIsBiggerCredit() {
        BigDecimal amount = BigDecimal.valueOf(10000);
        BigDecimal withdraw = creditAccount.withdraw(amount);

        Assertions.assertEquals(BigDecimal.ZERO, withdraw);
        Assertions.assertEquals(BigDecimal.valueOf(3000), creditAccount.getBalance());
    }

    /**
     * Снятие нулевой суммы
     */
    @Test
    void amountIsZero() {
        BigDecimal amount = BigDecimal.valueOf(0);
        BigDecimal withdraw = creditAccount.withdraw(amount);

        Assertions.assertEquals(BigDecimal.ZERO, withdraw);
        Assertions.assertEquals(3000, creditAccount.getBalance().intValue());
    }

    /**
     * Снятие отрицательной суммы
     */
    @Test
    void amountIsNegative() {
        BigDecimal amount = BigDecimal.valueOf(-1500);
        BigDecimal initialBalance = creditAccount.getBalance();
        BigDecimal withdraw = creditAccount.withdraw(amount);

        Assertions.assertEquals(BigDecimal.ZERO, withdraw);
        Assertions.assertEquals(initialBalance, creditAccount.getBalance());
    }

    /**
     * Снятие копейки (0.01 в рублях), проверка на снятие маленьких значений
     */
    @Test
    void withdrawKopeck() {
        BigDecimal amount = BigDecimal.valueOf(0.01);
        BigDecimal withdraw = creditAccount.withdraw(amount);

        Assertions.assertEquals(BigDecimal.valueOf(0.01), withdraw);
        Assertions.assertEquals(BigDecimal.valueOf(2999.9899), creditAccount.getBalance());
    }

    /**
     * Снятие BigDecimal, проверка на снятие больших значений
     */
    @Test
    void withdrawBigDecimal() {
        creditAccount = new DebitAccount(12345L, BigDecimal.valueOf(1000000000000.00), "Name");
        BigDecimal amount = BigDecimal.valueOf(999999999999.99);
        BigDecimal withdraw = creditAccount.withdraw(amount);

        Assertions.assertEquals(BigDecimal.valueOf(999999999999.99), withdraw);
        Assertions.assertEquals(BigDecimal.valueOf(000000000000.01), creditAccount.getBalance());
    }

    /**
     * Снятие суммы с нулевого баланса
     */
    @Test
    void withdrawZeroBalance() {
        creditAccount = new DebitAccount(12345L, BigDecimal.ZERO, "Name");
        BigDecimal amount = BigDecimal.valueOf(100);
        BigDecimal withdraw = creditAccount.withdraw(amount);

        Assertions.assertEquals(BigDecimal.ZERO, withdraw);
        Assertions.assertEquals(BigDecimal.ZERO, creditAccount.getBalance());
    }

    /**
     * Проверка правильности работы комиссии
     */
    @Test
    void correctCalculateCommission() {
        BigDecimal amount = BigDecimal.valueOf(100);
        BigDecimal withdraw = creditAccount.withdraw(amount);

        Assertions.assertEquals(BigDecimal.valueOf(100), withdraw);
        Assertions.assertEquals(2899, creditAccount.getBalance().intValue());
    }

    /**
     * Создание счета через builder
     */
    @Test
    void correctBuilder() {
        CreditAccount newAccount = CreditAccount.builder()
                .accountNumber(1L)
                .balance(BigDecimal.valueOf(3000))
                .accountHolder("Name1")
                .creditLimit(BigDecimal.valueOf(-3000))
                .build();

        Assertions.assertEquals(1, newAccount.getAccountNumber());
        Assertions.assertEquals(BigDecimal.valueOf(3000), newAccount.getBalance());
        Assertions.assertEquals("Name1", newAccount.getAccountHolder());
        Assertions.assertEquals(BigDecimal.valueOf(-3000), newAccount.getCreditLimit());
    }

    /**
     * Создание счета без кредитного лимита
     */
    @Test
    void createdAccountWithoutCredit() {
        CreditAccount newAccount = CreditAccount.builder()
                .accountNumber(1L)
                .balance(BigDecimal.valueOf(1))
                .accountHolder("Name1")
                .build();

        Assertions.assertEquals(BigDecimal.valueOf(-5000), newAccount.getCreditLimit());
    }
}