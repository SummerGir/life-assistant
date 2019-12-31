package com.app.core.memberInfo.dao;

import com.app.core.memberInfo.entity.CoreMemberInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import util.dataManage.GenericDao;
@Repository("com.app.core.memberInfo.dao.CoreMemberInfoDao")
public interface CoreMemberInfoDao extends
        JpaRepository<CoreMemberInfoEntity,String>,
        JpaSpecificationExecutor<CoreMemberInfoEntity>,
        GenericDao<CoreMemberInfoEntity,String>{
}
