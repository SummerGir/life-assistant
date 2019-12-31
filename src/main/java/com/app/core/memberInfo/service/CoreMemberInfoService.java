package com.app.core.memberInfo.service;

import com.app.core.memberInfo.dao.CoreMemberInfoDao;
import com.app.core.memberInfo.entity.CoreMemberInfoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import util.context.Context;
import util.dataManage.GenericDao;
import util.dataManage.GenericService;
import util.spring.ApplicationContext;

@Service("com.app.core.memberInfo.service.CoreMemberInfoService")
public class CoreMemberInfoService extends GenericService<CoreMemberInfoEntity,String>{

    @Autowired
    protected CoreMemberInfoDao dao;

    @Override
    protected GenericDao<CoreMemberInfoEntity, String> getDaoInstance() {
        return dao;
    }

    public void setMember(){
        CoreMemberInfoEntity entity = findOne("0dfb8bd5-b87c-11e7-96df-64510645b30a");
        System.out.println("当前登录人："+entity.getMemeberName());
        Context.setMember(entity);
    }

    public static CoreMemberInfoService getInstance(){
        return ApplicationContext.getCurrent().getBean(CoreMemberInfoService.class);
    }
}
