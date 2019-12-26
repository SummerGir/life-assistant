package com.app.core.memberTree.service;

import com.app.core.memberTree.dao.CoreMenuTreeDao;
import com.app.core.memberTree.entity.CoreMenuTreeInfoEntity;
import org.apache.commons.lang.StringUtils;
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

@Service("com.app.core.memberTree.service.CoreMenuTreeInfoService")
public class CoreMenuTreeInfoService extends GenericService<CoreMenuTreeInfoEntity,String> {
    @Autowired
    protected CoreMenuTreeDao dao;

    @Autowired
    protected EntityManager entityManager;

    public static CoreMenuTreeInfoService getInstance(){
        return ApplicationContext.getCurrent().getBean(CoreMenuTreeInfoService.class);
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

    public List<Map<String,Object>> getMenuTree(List<Map<String,Object>> list, String pl){

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


    /**
     * 移动菜单
     * @param treeId
     * @param type：true(上移)、false(下移)
     */
    @Transactional
    public void moveTree(String treeId,boolean type) throws Exception{
        CoreMenuTreeInfoEntity entity = dao.findOne(treeId);
        if(entity == null){
            return;
        }
        int menuLevel = entity.getMenuLevel();
        if(type){
            menuLevel--;
        }else {
            menuLevel++;
        }
        String outlineLevel = entity.getOutlineLevel();
        if(outlineLevel.split("\\.").length>1){
            outlineLevel = outlineLevel.substring(0,outlineLevel.lastIndexOf("."))+"."+menuLevel;
        }else {
            outlineLevel = String.valueOf(menuLevel);
        }

        CoreMenuTreeInfoEntity entity1 = findOneByOutlineLevel(outlineLevel);
        if(entity1 == null){
            return;
        }
        moveChildren(entity1.getOutlineLevel(),"temp");
        moveChildren(entity.getOutlineLevel(),entity1.getOutlineLevel());
        moveChildren("temp",entity.getOutlineLevel());

        entity1.setMenuLevel(entity.getMenuLevel());
        entity1.setOutlineLevel(entity.getOutlineLevel());
        entity.setMenuLevel(menuLevel);
        entity.setOutlineLevel(outlineLevel);

        List<CoreMenuTreeInfoEntity>  list = new ArrayList<>();
        list.add(entity);
        list.add(entity1);
        save(list);
    }

    private void moveChildren(String oldParen,String newParen){
        String sql = "UPDATE core_menu_tree_info a set a.OUTLINE_LEVEL=concat(:newParen,'.',a.menu_level) WHERE LEFT (a.outline_level,LENGTH(:oldParen)) = :oldRaren and LENGTH(a.outline_level)>LENGTH(:oldParen)";
        entityManager.createNativeQuery(sql).setParameter("oldParen",oldParen).setParameter("newParen",newParen).executeUpdate();
    }


    public CoreMenuTreeInfoEntity findOneByOutlineLevel(String outlineLevel){
        String sql = "SELECT en from CoreMenuTreeInfoEntity en WHERE en.outlineLevel=:outlineLevel";
        List<CoreMenuTreeInfoEntity> list = entityManager.createQuery(sql).setParameter("outlineLevel",outlineLevel).getResultList();
        if(list != null && list.size()>0){
            return list.get(0);
        }else {
            return null;
        }

    }

    public CoreMenuTreeInfoEntity findOneByCode(String code){
        List<CoreMenuTreeInfoEntity> list = entityManager.createQuery("select en from CoreMenuTreeInfoEntity en,CoreMenuUrlInfoEntity  en2 where en.urlId = en2.urlId and en2.code = :code").setParameter("code",code).getResultList();
        if(list != null && list.size()>0){
            return list.get(0);
        }else
            return null;
    }

    //根据上级，得到子级下一个排序数字
    public int getMenuLevelByParLevel(String outlineLevel){
        String sql = "SELECT MAX(a.MENU_LEVEL) FROM core_menu_tree_info a WHERE LEFT(a.outline_level,LENGTH(:outlineLevel))=:outlineLevel and LENGTH(a.outlineLevel)>LENGTH(:outlineLevel) ";
        List list = entityManager.createNativeQuery(sql).setParameter("outlineLevel",outlineLevel).getResultList();
        int n = 1;
        if(list != null && list.size()>0){
            n = Integer.parseInt(list.get(0).toString()) + 1;
        }
        return n;
    }
}
