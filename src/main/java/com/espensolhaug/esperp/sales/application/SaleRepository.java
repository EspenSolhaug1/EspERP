package com.espensolhaug.esperp.sales.application;

import com.espensolhaug.esperp.sales.domain.Sale;
import java.util.List;

public interface SaleRepository {
    void save(Sale sale);
    List<Sale> findAll();
}
