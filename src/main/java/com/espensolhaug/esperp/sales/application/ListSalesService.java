package com.espensolhaug.esperp.sales.application;

import com.espensolhaug.esperp.sales.domain.Sale;

import java.util.Collections;
import java.util.List;

public class ListSalesService {
    private final SaleRepository repository;
    public ListSalesService(SaleRepository repository) {
        this.repository = repository;
    }
    public List<Sale> getAllSales() {
        return Collections.unmodifiableList(repository.findAll());
    }
}
