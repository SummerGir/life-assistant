package com.app.typeinfo.service;


import com.app.typeinfo.dao.AppTypeInfoDao;
import com.app.typeinfo.entity.AppTypeInfoEntity;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import util.dataManage.GenericDao;
import util.dataManage.GenericService;
import util.spring.ApplicationContext;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("com.app.typeinfo.service.AppTypeInfoService")
public class AppTypeInfoService extends GenericService<AppTypeInfoEntity,String>{


    @Autowired
    protected AppTypeInfoDao dao;

    @Autowired
    protected EntityManager entityManager;

    protected AppTypeInfoService(){};

    public static AppTypeInfoService getInstance(){
        return ApplicationContext.getCurrent().getBean(AppTypeInfoService.class);
    }

    @Override
    protected GenericDao<AppTypeInfoEntity, String> getDaoInstance() {
        return dao;
    }

    @Transactional
    public void save(AppTypeInfoEntity entity) throws Exception{
        dao.save(entity);
    }

    @Transactional
    public void save(List<AppTypeInfoEntity> list) throws Exception{
        dao.save(list);
    }

    @Transactional
    public void delete(String AppTypeInfoId) throws Exception {
        dao.delete(AppTypeInfoId);
    }

    //得到菜单列表
    public List<Map<String,Object>> getMainInfo(String mainId,String searchKey,int page,int rows) throws Exception{
        String baseSQL = "select ati.TYPE_ID,ati.TYPE_NAME,ati.TYPE_CODE from app_type_info ati where 1=1";
        Map<String,Object> values = new HashMap();
        if(StringUtils.isNotBlank(mainId)){
            values.put("mainId",mainId);
            baseSQL += " and ati.TYPE_ID=:mainId";
        }if(StringUtils.isNotBlank(searchKey)){
            values.put("searchKey",searchKey);
            baseSQL += " and (locate(:searchKey,ati.TYPE_NAME)>0 or (:searchKey,ati.TYPE_CODE)>0)";
        }
        baseSQL += "oeder by ati.SYS_TIME desc";
        String[] fields = {"typeId","typeName","typeCode"};
        List<Map<String,Object>> list = getNativeMapList(entityManager,baseSQL,values,fields,page,rows);

        for(Map<String,Object>m:list){
            for(Map.Entry<String,Object> e : m.entrySet()){
                if(e.getValue() == null){
                    m.put(e.getKey(),"");
                }
            }
        }

        return list;
    }

    public int getMainCount(String mainId,String searchKey){
        String baseSQL = "select count(1) from app_type_info ati where 1=1 ";
        if(StringUtils.isNotBlank(mainId)){
            baseSQL += "and ati.TYPE_ID=:mainId";
        }if(StringUtils.isNotBlank(searchKey)){
            baseSQL += "and (locate(:searchKey,ati.TYPE_NAME)>0 or locate(:searchKey,ati.TYPE_CODE)>0)";
        }
        Query query = entityManager.createNativeQuery(baseSQL);
        if(StringUtils.isNotBlank(mainId)){
            query.setParameter("mainId",mainId);
        }if(StringUtils.isNotBlank(searchKey)){
            query.setParameter("searchKey",searchKey);
        }
        int count = 0;
        List list = query.getResultList();
        if(list !=null && list.size()>0){
            count = Integer.parseInt(list.get(0).toString());

        }
        return count;
    }

    public AppTypeInfoEntity findOneByTypeCode(String typeCode){
        String baseSQL = "select ati.TYPE_ID,ati.TYPE_NAME,ati.TYPE_CODE,ati.SYS_TIME,ati.MEMBER_ID from app_type_info ati where 1=1";
        if(StringUtils.isNotBlank(typeCode)){
            baseSQL += " ati.TYPE_CODE=:typeCode";
        }
        Query query = entityManager.createNativeQuery(baseSQL);
        if(StringUtils.isNotBlank(typeCode)){
            query.setParameter("typeCode",typeCode);
        }

        List<AppTypeInfoEntity> list = query.getResultList();
        if(list != null && list.size()>0){
            return list.get(0);
        }else {
            return null;
        }
    }

















}
