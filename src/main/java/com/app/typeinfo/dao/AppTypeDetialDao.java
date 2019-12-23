package com.app.typeinfo.dao;

import com.app.typeinfo.entity.AppTypeDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import util.dataManage.GenericDao;

import java.util.List;

@Repository("com.app.typeinfo.dao.AppTypeDetialDao")
public interface AppTypeDetialDao extends
        JpaRepository<AppTypeDetailEntity,String>,
        JpaSpecificationExecutor<AppTypeDetailEntity>,
        GenericDao<AppTypeDetailEntity,String>{
    @Query("select tde from AppTypeDetailEntity tde where tde.typeId=:typeId")
    List<AppTypeDetailEntity> findByTypeDetailId(@Param("typeId") String typeId);
}
