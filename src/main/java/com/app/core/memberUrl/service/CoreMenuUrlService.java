package com.app.core.memberUrl.service;

import com.app.core.memberUrl.dao.CoreMenuUrlDao;
import com.app.core.memberUrl.entity.CoreMenuUrlInfoEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

@Service("com.app.core.memberUrl.service.CoreMenuUrlService")
public class CoreMenuUrlService extends GenericService<CoreMenuUrlInfoEntity,String> {

    @Autowired
    protected CoreMenuUrlDao dao;

    @Autowired
    protected EntityManager entityManager;

    public CoreMenuUrlService(){};


    @Override
    protected GenericDao<CoreMenuUrlInfoEntity, String> getDaoInstance() {
        return dao;
    }

    public static CoreMenuUrlService getInstance(){
        return ApplicationContext.getCurrent().getBean(CoreMenuUrlService.class);
    }

    @Transactional
    public  void save(CoreMenuUrlInfoEntity entity){
        dao.save(entity);
    }

    @Transactional
    public void save(List<CoreMenuUrlInfoEntity> list){
        dao.save(list);
    }

    @Transactional
    public void delete(String mainId){
        dao.delete(mainId);
    }

    public List<Map<String,Object>> getMainInfo(String mainId,String searchKey,int page,int rows) throws  Exception{
        Map<String,Object> values = new HashMap();
        String baseSQL = "SELECT cmui.URL_ID,cmui.TITLE,cmui.CODE,cmui.URL,cmui.PARAMETER,cmui.SYS_TIME FROM core_menu_url_info cmui where 1=1 ";
        if(StringUtils.isNotBlank(mainId)){
            values.put("mainId",mainId);
            baseSQL += " and cmui.URL_ID=:mainId";
        }
        if(StringUtils.isNotBlank(searchKey)){
            values.put("searchKey",searchKey);
            baseSQL += " and (locate(:searchKey,cmui.TITLE)>0 or locate(:searchKey,cmui.CODE)>0)";
        }
        baseSQL += " order by cmui.SYS_TIME desc";
        String[] fields = {"urlId", "urlTitle", "urlCode", "urlStr", "parameter","sysTime"};
        List<Map<String,Object>> list = getNativeMapList(entityManager,baseSQL,values,fields,page,rows);
        for(Map<String,Object> map:list){
            for(Map.Entry<String,Object> entry: map.entrySet()){
                if(entry.getValue() == null){
                    map.put(entry.getKey(),"");
                }
                if("sysTime".equals(entry.getKey().toString())){
                    map.put(entry.getKey(),entry.getValue().toString().split(" ")[0]);
                }
            }
        }
        return list;
    }


    public int getMainCount(String mainId,String searchKey){
        String baseSQL = "SELECT COUNT(1) FROM core_menu_url_info cmui WHERE 1=1";
        if(StringUtils.isNotBlank(mainId)){
            baseSQL += " and cmui.URL_ID=:mainId";
        }
        if(StringUtils.isNotBlank(searchKey)){
            baseSQL += " and (locate(:searchKey,cmui.TITLE)>0 or locate(:searchKey,cmui.CODE)>0)";
        }

        Query query = entityManager.createNativeQuery(baseSQL);
        if(StringUtils.isNotBlank(mainId)){
            query.setParameter("mainId",mainId);
        }
        if(StringUtils.isNotBlank(searchKey)){
            query.setParameter("searchKey",searchKey);
        }
        List list =  query.getResultList();
        int count = 0;
        if(list != null && list.size()>0){
            count = Integer.parseInt(list.get(0).toString());
        }
        return count;
    }

    public StringBuffer getMenuUrlOptions(Boolean isHave){
        StringBuffer stringBuffer = new StringBuffer();
        if(isHave){
            stringBuffer.append("<option value='' style=='display:none'>---空菜单---</option>");
        }
        try{
            List<Map<String,Object>> list = getMainInfo(null,null,1,-1);
            for(Map<String,Object> map:list){
                stringBuffer.append("<option value='"+ map.get("urlId").toString()+"'>"+ map.get("urlTitle").toString()+"</option>");
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return stringBuffer;
    }



































}
