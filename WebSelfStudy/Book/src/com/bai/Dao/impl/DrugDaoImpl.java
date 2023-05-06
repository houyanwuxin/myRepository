package com.bai.Dao.impl;

import com.bai.Dao.DrugDao;
import com.bai.pojo.Drug;

import java.util.List;

public class DrugDaoImpl extends BaseDao implements DrugDao {
    @Override
    public int addDrug(Drug drug) {
        String sql = "insert into t_drug(`name`,`author`,`price`,`sales`,`stock`,`img_path`) values(?,?,?,?,?,?)";
        return update(sql,
                drug.getName(),
                drug.getPrice(),drug.getSales(),
                drug.getStock(),drug.getImgPath());
    }

    @Override
    public int deleteDrugById(Integer id) {
        String sql = "delete from t_drug where id = ?";
        return update(sql, id);
    }

    @Override
    public int updateDrug(Drug drug) {
        String sql = "update t_drug set `name`=?,`author`=?,`price`=?,`sales`=?,`stock`=?,`img_path`=? where id = ?";
        return
                update(sql,drug.getName(),
                        drug.getPrice(),drug.getSales(),
                        drug.getStock(),drug.getImgPath(),drug.getId());
    }

    @Override
    public Drug queryDrugById(Integer id) {
        String sql = "select `id` , `name` , `author` , `price` , `sales` , `stock` , `img_path` imgPath from t_drug where id = ?";
        return queryForOne(Drug.class, sql,id);
    }

    @Override
    public List<Drug> queryDrugs() {
        String sql = "select `id` , `name` , `author` , `price` , `sales` , `stock` , `img_path` imgPath from t_drug";
        return queryForList(Drug.class, sql);
    }

    @Override
    public Integer queryForPageTotalCount() {
        String sql = "select count(*) from t_drug";
        Number count = (Number) queryForSingleValue(sql);
        return count.intValue();
    }

    @Override
    public List<Drug> queryForPageItems(int begin, int pageSize) {
        String sql = "select `id` , `name` , `author` , `price` , `sales` , `stock` , `img_path` imgPath from t_drug limit ?,?";
        return queryForList(Drug.class,sql,begin,pageSize);
    }
}
