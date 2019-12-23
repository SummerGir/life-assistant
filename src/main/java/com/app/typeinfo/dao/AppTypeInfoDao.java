package com.app.typeinfo.dao;

import com.app.typeinfo.entity.AppTypeInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import util.dataManage.GenericDao;


@Repository("com.app.typeinfo.dao.AppTypeInfoDao")

public interface AppTypeInfoDao extends JpaRepository<AppTypeInfoEntity,String>,JpaSpecificationExecutor<AppTypeInfoEntity>,GenericDao<AppTypeInfoEntity,String> {

    //根据typeCode,得到类型对象
    @Query("select ati from AppTypeInfoEntity ati where ati.typeCode=:typeCode")
    AppTypeInfoEntity findByTypeCode(@Param("typeCode") String typeCode);
}
