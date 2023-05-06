package com.bai.service;

import com.bai.pojo.Drug;
import com.bai.pojo.Page;

import java.util.List;

public interface DrugService {
    public void addDrug(Drug drug);
    public void deleteDrugById(Integer id);
    public void updateDrug(Drug drug);
    public Drug queryDrugById(Integer id);
    public List<Drug> queryDrugs();
    public Page<Drug> page(int pageNo, int pageSize);
}
