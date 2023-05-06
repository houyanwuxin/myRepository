package com.bai.Dao;

import com.bai.pojo.Drug;

import java.util.List;

public interface DrugDao {
    public int addDrug(Drug drug);
    public int deleteDrugById(Integer id);
    public int updateDrug(Drug drug);
    public Drug queryDrugById(Integer id);
    public List<Drug> queryDrugs();
    public Integer queryForPageTotalCount();
    public List<Drug> queryForPageItems(int begin, int pageSize);

}
