package com.app.cost.service;

import com.app.cost.dao.AppDailyCostInfoDao;
import com.app.cost.entity.AppDailyCostInfoEntity;
import com.app.typeinfo.entity.AppTypeDetailEntity;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import util.dataManage.GenericDao;
import util.dataManage.GenericService;
import util.spring.ApplicationContext;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Map;

@Service("com.app.cost.service.AppDailyCostInfoService")
public class AppDailyCostInfoService extends GenericService<AppDailyCostInfoEntity,String>{

    @Autowired
    protected AppDailyCostInfoDao dao;

    @Autowired
    protected EntityManager entityManager;

    @Override
    protected GenericDao<AppDailyCostInfoEntity, String> getDaoInstance() {
        return dao;
    }

    public AppDailyCostInfoService getInstance(){
        return ApplicationContext.getCurrent().getBean(AppDailyCostInfoService.class);
    }

    @Transactional
    public void save(AppDailyCostInfoEntity entity){
        dao.save(entity);
    }
    @Transactional
    public void save(List<AppDailyCostInfoEntity> list){
        dao.save(list);
    }
    @Transactional
    public void delete(String mainId){
       dao.delete(mainId);
    }

    public List<Map<String,Object>> getMainInfo(String mainId, String searchKey,String beingTime,String endTime,String typeDetailId, int Page, int rows){
        String basicSQL = "select adci.TITLE,adci.COST_TIME,adci.COST_NUM,adci.COST_PRICE,adci.PAY_MONEY,adci.TYPE_DETAIL_ID,adci.SYS_TIME from app_daily_cost_info adci where 1=1";
        return null;
    }

    public int getCount(String mainId,String searchKey){
        return 0;
    }




}




























