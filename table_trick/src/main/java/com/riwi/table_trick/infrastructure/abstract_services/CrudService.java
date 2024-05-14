package com.riwi.table_trick.infrastructure.abstract_services;

import org.springframework.data.domain.Page;

import com.riwi.table_trick.util.enums.SortType;

public interface CrudService <RQ, RS, ID>{
    public void delete(ID id);
    public RS update(RQ request, String id);
    public RS create(RQ request);
    public Page<RS> getAll(int page, int size, SortType sort);
}
