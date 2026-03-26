package com.espensolhaug.esperp.sales.infrastructure;

import com.espensolhaug.esperp.sales.application.SaleRepository;
import com.espensolhaug.esperp.sales.domain.Sale;
import java.util.ArrayList;
import java.util.List;

public class FakeSaleRepository implements SaleRepository {
    private final List<Sale> database = new ArrayList<>();

    public void save(Sale sale) {
        this.database.add(sale);
    }

    public List<Sale> findAll() {
        return new ArrayList<>(database);
    }
}
