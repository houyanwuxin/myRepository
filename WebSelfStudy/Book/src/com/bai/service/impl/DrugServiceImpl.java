package com.bai.service.impl;

import com.bai.Dao.DrugDao;
import com.bai.Dao.impl.DrugDaoImpl;
import com.bai.pojo.Drug;
import com.bai.pojo.Page;
import com.bai.service.DrugService;

import java.util.List;

public class DrugServiceImpl implements DrugService {
    private DrugDao drugDao = new DrugDaoImpl();
    @Override
    public void addDrug(Drug drug) {
        drugDao.addDrug(drug);
    }
    @Override
    public void deleteDrugById(Integer id) {
        drugDao.deleteDrugById(id);
    }
    @Override
    public void updateDrug(Drug drug) {
        drugDao.updateDrug(drug);
    }
    @Override
    public Drug queryDrugById(Integer id) {
        return drugDao.queryDrugById(id);
    }
    @Override
    public List<Drug> queryDrugs() {
        return drugDao.queryDrugs();
    }

    @Override
    public Page<Drug> page(int pageNo, int pageSize) {
        Page<Drug> page = new Page<Drug>();
// 设置每页显示的数量
        page.setPageSize(pageSize);
// 求总记录数
        Integer pageTotalCount = drugDao.queryForPageTotalCount();
// 设置总记录数
        page.setPageTotalCount(pageTotalCount);
// 求总页码
        Integer pageTotal = pageTotalCount / pageSize;
        if (pageTotalCount % pageSize > 0) {
            pageTotal+=1;
        }
// 设置总页码
        page.setPageTotal(pageTotal);
// 设置当前页码
        page.setPageNo(pageNo);
// 求当前页数据的开始索引
        int begin = (page.getPageNo() - 1) * pageSize;
// 求当前页数据
        List<Drug> items = drugDao.queryForPageItems(begin,pageSize);
// 设置当前页数据
        page.setItems(items);
        return page;
    }
}
