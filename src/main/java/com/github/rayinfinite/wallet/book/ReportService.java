package com.github.rayinfinite.wallet.book;

import com.github.rayinfinite.wallet.model.ChartVO;
import com.github.rayinfinite.wallet.model.book.Book;
import com.github.rayinfinite.wallet.model.transaction.Transaction;
import com.github.rayinfinite.wallet.transaction.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReportService {
    private final TransactionService transactionService;
    private final BookService bookService;

    public List<BigDecimal> overview() {
        List<Book> list = bookService.list();
        BigDecimal totalIncome = BigDecimal.ZERO;
        BigDecimal totalExpense = BigDecimal.ZERO;
        BigDecimal totalBalance = BigDecimal.ZERO;
        for (Book book : list) {
            totalIncome = totalIncome.add(book.getIncome());
            totalExpense = totalExpense.add(book.getExpense());
            totalBalance = totalBalance.add(book.getBalance());
        }
        return List.of(totalIncome, totalExpense, totalBalance);
    }

    public List<List<ChartVO>> reportBalance() {
        List<List<ChartVO>> result = new ArrayList<>();
        List<ChartVO> assetChart = new ArrayList<>();
        List<ChartVO> debtChart = new ArrayList<>();

        List<Book> list = bookService.list();
        list.forEach(book -> assetChart.add(new ChartVO(book.getName(), book.getIncome())));
        list.forEach(book -> debtChart.add(new ChartVO(book.getName(), book.getExpense())));

        result.add(assetChart);
        result.add(debtChart);
        return result;
    }

    public List<ChartVO> reportCategory(int type) {
        List<ChartVO> result = new ArrayList<>();
        Page<Transaction> page = transactionService.getPage(1, 100000);
        page.forEach(transaction -> {
            if (transaction.getCategory().getType() == type) {
                result.add(new ChartVO(transaction.getCategory().getName(), transaction.getAmount().abs()));
            }
        });
        Map<String, BigDecimal> resultMap = result.stream()
                .collect(Collectors.groupingBy(
                        ChartVO::getX,
                        Collectors.reducing(BigDecimal.ZERO, ChartVO::getY, BigDecimal::add)
                ));
        return resultMap.entrySet().stream()
                .map(entry -> new ChartVO(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }

    public List<ChartVO> reportExpenseCategory() {
        return reportCategory(0);
    }

    public List<ChartVO> reportIncomeCategory() {
        return reportCategory(1);
    }
}
