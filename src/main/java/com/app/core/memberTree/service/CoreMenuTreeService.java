package com.app.core.memberTree.service;

import com.app.core.memberTree.dao.CoreMenuTreeDao;
import com.app.core.memberTree.entity.CoreMenuTreeInfoEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import util.dataManage.GenericDao;
import util.dataManage.GenericService;
import util.spring.ApplicationContext;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("com.app.core.memberTree.service.CoreMenuTreeService")
public class CoreMenuTreeService extends GenericService<CoreMenuTreeInfoEntity,String>{

    @Autowired
    protected CoreMenuTreeDao dao;

    @Autowired
    protected EntityManager entityManager;

    public CoreMenuTreeService(){}

    public static CoreMenuTreeService getInstance(){
        return ApplicationContext.getCurrent().getBean(CoreMenuTreeService.class);
    }

    @Override
    protected GenericDao<CoreMenuTreeInfoEntity, String> getDaoInstance() {
        return dao;
    }

    @Transactional
    public void save(CoreMenuTreeInfoEntity entity)throws Exception{
        dao.save(entity);
    }
    @Transactional
    public void save(List<CoreMenuTreeInfoEntity> list)throws Exception{
        dao.save(list);
    }
    @Transactional
    public void delete(String mainId)throws Exception{
        //删掉这个要把其下的所有都删除掉
        String sql = "delete a from core_menu_tree_info a,core_menu_tree_info b where b.MENUID=:mainid and left(a.OUTLINE_LEVEL,length(b.OUTLINE_LEVEL))=b.OUTLINE_LEVEL and length(a.OUTLINE_LEVEL)>=length(b.OUTLINE_LEVEL)";

        entityManager.createNativeQuery(sql).setParameter("mainId",mainId).executeUpdate();
    }

    public List<Map<String,Object>> getMainInfo(String mainId, String isShow)throws Exception{
        Map<String,Object> values = new HashMap<>();
        String baseSQL = "select cmti.MENU_ID,cmti.MENU_LEVEL,cmti.OUTLINE_LEVEL,cmti.TITLE,cmti.ICON,cmti.`TYPE`,cmui.CODE,cmti.URL_ID,cmui.URL,cmui.PARAMETER,cmti.IS_SHOW from core_menu_tree_info cmti left join core_menu_url_info cmui on cmti.URL_ID = cmui.URL_ID where 1 = 1";
        if(StringUtils.isNotBlank(mainId)){
            baseSQL += " and cmti.MENU_ID=:mainId";
            values.put("mainId",mainId);
        }
        if(StringUtils.isNotBlank(isShow)){
            baseSQL += " and cmti.IS_SHOW=:isShow";
            values.put("isShow",isShow);
        }
        baseSQL+=" order by substr(cmti.OUTLINE_LEVEL,1,instr(cmti.OUTlINE_LEVEL,'.')-1),cmti.menu_level";

        String fields[] = {"menuId", "menuLevel", "outlineLevel", "title", "icon","type","code","urlId", "url", "parameter","isShow"};


        List<Map<String,Object>> list = getNativeMapList(entityManager,baseSQL,values,fields,1,-1);

        for (Map<String,Object> m:list){
            for (Map.Entry<String,Object> entry:m.entrySet()){
                if(entry.getValue() == null){
                    m.put(entry.getKey(),"");
                }
            }
        }

        return list;
    }

    public List<Map<String,Object>> getMenuTree(List<Map<String,Object>> list){
        CoreMenuTreeInfoEntity entity = findOne("root");
        List<Map<String,Object>> listMenu = new ArrayList<>();
        Map<String,Object> map = new HashMap<>();
        map.put("id",entity.getMenuId());
        map.put("text",entity.getTitle());
        map.put("outlineLevel",entity.getOutlineLevel());
        map.put("iconCls",entity.getIcon());
        map.put("state","open");

        List<Map<String,Object>> lc = getMenuTree(list,entity.getOutlineLevel());
        if(lc.size()>0){
            map.put("children",lc);
        }
        listMenu.add(map);
        return listMenu;
    }

    public List<Map<String,Object>> getMenuTree(List<Map<String,Object>> list,String pl){

        List<Map<String,Object>> listChildren = new ArrayList<>();
        for(Map m:list){
            boolean isC = false;
            String outlineLevel = m.get("outlineLevel").toString();
            if(outlineLevel.split("\\.").length>1){
                String parentLevel = outlineLevel.substring(0,outlineLevel.lastIndexOf("."));
                if(parentLevel.equals(pl)){
                    isC = true;
                }
            }else if("0".equals(pl)){
                isC = true;
            }

            if(isC){
                m.put("id",m.get("menuId"));
                m.put("text",m.get("title"));
                List<Map<String,Object>> lc = getMenuTree(list,outlineLevel);
                if(lc.size()>0){
                    m.put("children",lc);
                    m.put("state","closed");
                }
                listChildren.add(m);
            }
        }
        return listChildren;
    }





























}
