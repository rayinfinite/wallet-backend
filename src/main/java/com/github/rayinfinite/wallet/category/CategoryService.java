package com.github.rayinfinite.wallet.category;

import com.github.rayinfinite.wallet.exception.DefaultException;
import com.github.rayinfinite.wallet.model.CurrentSession;
import com.github.rayinfinite.wallet.model.category.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CurrentSession currentSession;

    public Category get(Long id) {
        Category category = categoryRepository.findById(id).orElse(null);
        if (category == null) {
            throw new DefaultException("Category not exist");
        }
        if (!category.getBook().getId().equals(currentSession.getBook().getId())) {
            throw new DefaultException("Category not in current book");
        }
        return category;
    }

    public void add(long id, Category category) {
        category.setBook(currentSession.getBook());
        if (id != -1) {
            Category parent = get(id);
            parent.getChild().add(category);
            categoryRepository.save(parent);
        }
        categoryRepository.save(category);
    }

    public void delete(Long id) {
        Category category = get(id);
        categoryRepository.delete(category);
    }

    public void update(Long id, Category category) {
        Category old = get(id);
        BeanUtils.copyProperties(category, old);
        categoryRepository.save(old);
    }

    public List<Category> list() {
        return categoryRepository.findByBookId(currentSession.getBook().getId());
    }

    public void init(){
        add(-1,new Category("Cash","atm",1));
        add(-1,new Category("Interest","monetization_on",1));
        add(-1,new Category("Others","universal_currency_alt",1));
        add(-1,new Category("Rental","apartment",1));
        add(-1,new Category("Salary","wallet",1));
        add(-1,new Category("Transfer","currency_exchange",1));
        add(-1,new Category("Investments","monitoring",1));

        add(-1,new Category("Bills, Utilities & Taxes","receipt_long",0));
        add(-1,new Category("Cash","atm",0));
        add(-1,new Category("Dining","restaurant",0));
        add(-1,new Category("Education","school",0));
        add(-1,new Category("Entertainment & Leisure","stadia_controller",0));
        add(-1,new Category("Fees & Charges","description",0));
        add(-1,new Category("Gifts & Donations","redeem",0));
        add(-1,new Category("Health & Fitness","sports_soccer",0));
        add(-1,new Category("Housing","house",0));
        add(-1,new Category("Insurance","health_and_safety",0));
        add(-1,new Category("Investment","monitoring",0));
        add(-1,new Category("Kids & Family","family_restroom",0));
        add(-1,new Category("Personal Care","dresser",0));
        add(-1,new Category("Services","support_agent",0));
        add(-1,new Category("Shopping","shopping_bag",0));
        add(-1,new Category("Transfer","currency_exchange",0));
        add(-1,new Category("Transportation","directions_car",0));
        add(-1,new Category("Travel","flight_takeoff",0));
    }
}
