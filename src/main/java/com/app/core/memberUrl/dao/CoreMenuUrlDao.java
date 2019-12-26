package com.app.core.memberUrl.dao;

import com.app.core.memberUrl.entity.CoreMenuUrlInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import util.dataManage.GenericDao;

@Repository("com.app.core.memberUrl.dao.CoreMenuUrlDao")
public interface CoreMenuUrlDao extends
        JpaRepository<CoreMenuUrlInfoEntity,String>,
        JpaSpecificationExecutor<CoreMenuUrlInfoEntity>,
        GenericDao<CoreMenuUrlInfoEntity,String>{
}
