package com.github.rayinfinite.wallet.model.transaction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

class AddTransactionDiffblueTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>default or parameterless constructor of {@link AddTransaction}
     *   <li>{@link AddTransaction#setAccountId(Long)}
     *   <li>{@link AddTransaction#setAmount(BigDecimal)}
     *   <li>{@link AddTransaction#setCategoryId(Long)}
     *   <li>{@link AddTransaction#setNotes(String)}
     *   <li>{@link AddTransaction#setTime(String)}
     *   <li>{@link AddTransaction#setType(String)}
     *   <li>{@link AddTransaction#toString()}
     *   <li>{@link AddTransaction#getAccountId()}
     *   <li>{@link AddTransaction#getAmount()}
     *   <li>{@link AddTransaction#getCategoryId()}
     *   <li>{@link AddTransaction#getNotes()}
     *   <li>{@link AddTransaction#getTime()}
     *   <li>{@link AddTransaction#getType()}
     * </ul>
     */
    @Test
    void testConstructor() {
        AddTransaction actualAddTransaction = new AddTransaction();
        actualAddTransaction.setAccountId(1L);
        BigDecimal amount = new BigDecimal("2.3");
        actualAddTransaction.setAmount(amount);
        actualAddTransaction.setCategoryId(1L);
        actualAddTransaction.setNotes("Notes");
        actualAddTransaction.setTime("Time");
        actualAddTransaction.setType("Type");
        String actualToStringResult = actualAddTransaction.toString();
        Long actualAccountId = actualAddTransaction.getAccountId();
        BigDecimal actualAmount = actualAddTransaction.getAmount();
        Long actualCategoryId = actualAddTransaction.getCategoryId();
        String actualNotes = actualAddTransaction.getNotes();
        String actualTime = actualAddTransaction.getTime();
        assertEquals("AddTransaction(categoryId=1, amount=2.3, accountId=1, notes=Notes, type=Type, time=Time)",
                actualToStringResult);
        assertEquals("Notes", actualNotes);
        assertEquals("Time", actualTime);
        assertEquals("Type", actualAddTransaction.getType());
        assertEquals(1L, actualAccountId.longValue());
        assertEquals(1L, actualCategoryId.longValue());
        assertEquals(new BigDecimal("2.3"), actualAmount);
        assertSame(amount, actualAmount);
    }
}
