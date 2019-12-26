package com.app.cost.dao;

import com.app.cost.entity.AppDailyCostInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import util.dataManage.GenericDao;

@Repository("com.app.cost.dao.AppDailyCostInfoDao")
public interface AppDailyCostInfoDao extends
        JpaRepository<AppDailyCostInfoEntity,String>,
        JpaSpecificationExecutor<AppDailyCostInfoEntity>,
        GenericDao<AppDailyCostInfoEntity,String> {
}
