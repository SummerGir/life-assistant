package com.app.core.memberTree.dao;

import com.app.core.memberTree.entity.CoreMenuTreeInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import util.dataManage.GenericDao;

@Repository("com.app.core.memberTree.dao.CoreMenuTreeDao")
public interface CoreMenuTreeDao extends
        JpaRepository<CoreMenuTreeInfoEntity,String>,
        JpaSpecificationExecutor<CoreMenuTreeInfoEntity>,
        GenericDao<CoreMenuTreeInfoEntity,String>{
}
