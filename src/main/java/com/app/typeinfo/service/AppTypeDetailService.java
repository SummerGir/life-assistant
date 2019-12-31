package com.app.typeinfo.service;

import com.app.typeinfo.dao.AppTypeDetialDao;
import com.app.typeinfo.dao.AppTypeInfoDao;
import com.app.typeinfo.entity.AppTypeDetailEntity;
import com.app.typeinfo.entity.AppTypeInfoEntity;
import com.app.typeinfo.entity.TypeSelectEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import util.Utils;
import util.dataManage.GenericDao;
import util.dataManage.GenericService;
import util.spring.ApplicationContext;



import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.*;

@Service("com.app.typeinfo.service.AppTypeDetailService")
public class AppTypeDetailService extends GenericService<AppTypeDetailEntity,String>{

    @Autowired
    private AppTypeDetialDao detialdao;

    @Autowired
    private AppTypeInfoDao infoDao;

    @Autowired
    private AppTypeInfoService infoService;

    @Autowired
    private EntityManager entityManager;

    protected AppTypeDetailService(){};

    @Override
    protected GenericDao<AppTypeDetailEntity, String> getDaoInstance() {
        return detialdao;
    }

    public static AppTypeDetailService getInstance(){
        return ApplicationContext.getCurrent().getBean(AppTypeDetailService.class);
    }
    @Transactional
    public void save(AppTypeDetailEntity entity){
        detialdao.save(entity);
    }
    @Transactional
    public void save(List<AppTypeDetailEntity> list){
        detialdao.save(list);
    }
    @Transactional
    public void delete(String detialId){
        detialdao.delete(detialId);
    }
    @Transactional
    public void deleteByTypeId(String typeId){
        entityManager.createQuery("delete from AppTypeDetailEntity where typeId=:typeId").setParameter("typeId",typeId).executeUpdate();
    }

    @Transactional
    public AppTypeDetailEntity saveOne(String menuCode,String typeName){
        try {
            AppTypeInfoEntity typeInfoEntity = infoService.findOneByTypeCode(menuCode);
            AppTypeDetailEntity typeDetailEntity = new AppTypeDetailEntity();
            typeDetailEntity.setTypeDetailId(UUID.randomUUID().toString());
            typeDetailEntity.setTypeId(typeInfoEntity.getTypeId());
            typeDetailEntity.setDetailLevel(1);
            typeDetailEntity.setDetailName(typeName);
            typeDetailEntity.setDetailCode(Utils.getPinYins(typeName));
            typeDetailEntity.setDetailValue(typeDetailEntity.getDetailCode());
            typeDetailEntity.setComment("自动新增项");
            typeDetailEntity.setValid(true);
            save(typeDetailEntity);
            return typeDetailEntity;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    //得到菜单列表
    public List<Map<String,Object>> getMainInfo(String mianId, String searchKey, int page, int rows)throws Exception{
        String basicSQL = "SELECT atd.TYPE_DETAIL_ID,atd.DETAIL_NAME,atd.DETAIL_CODE,atd.DETAIL_VALUE,atd.DETAIL_LEVEL,atd.`COMMENT`,atd.IS_VALID FROM app_type_detail atd where 1=1";
        Map<String,Object> values = new HashMap<>();
        if(StringUtils.isNotBlank(mianId)){
            basicSQL += " and atd.TYPE_ID =:mianId";
            values.put("mianId",mianId);
        }
        if(StringUtils.isNotBlank(searchKey)){
            basicSQL += " and (locate(:searchKey,atd.DETAIL_NAME)>0 or (locatd(:searchKey,det.DETAIL_CODE)>0) or (locate(:searchKey,det.DETAIL_VALUE)>0))";
            values.put("searchKey",searchKey);
        }
        basicSQL += " order by atd.IS_VALID desc";
        String[] fields = {"typeDetailId","detailName","detailCode","detailValue","detailLevel","comment","isValid"};

        List<Map<String,Object>> list = getNativeMapList(entityManager,basicSQL,values,fields,page,rows);

        for(Map<String,Object> map : list){
            for(Map.Entry<String,Object> entry:map.entrySet()){
                if(entry.getValue() == null){
                    map.put(entry.getKey(),"");
                }else {
                    map.put(entry.getKey(),entry.getValue().toString());
                }
            }
        }

        return list;
    }

    public int getMainCount(String mainId,String searchKey){
        String basicSQL = "SELECT COUNT(1) FROM app_type_info ati join app_type_detail atd on ati.TYPE_ID = atd.TYPE_ID where 1=1";

        if(StringUtils.isNotBlank(mainId)){
            basicSQL += " and ati.TYPE_ID =:mainId";
        }
        if(StringUtils.isNotBlank(searchKey)){
            basicSQL += " and(locate(:searchKey,atd.DETAIL_NAME)>0 or locate(:searchKey,atd.DETAIL_CODE)>0 or (:searchKey,atd.DETAIL_VALUE)>0)";
        }
        Query query = entityManager.createNativeQuery(basicSQL);
        if(StringUtils.isNotBlank(mainId)){
            query.setParameter("mainId",mainId);
        }
        if(StringUtils.isNotBlank(searchKey)){
            query.setParameter("searchKey",searchKey);
        }

        int count = 0;
        List list = query.getResultList();
        if(list != null && list.size()>0){
            count =  Integer.parseInt(list.get(0).toString());
        }
        return count;
    }


    //类型选项
    public TypeSelectEntity getTypeSelect(String typeCode,String selectedTypeId){
        TypeSelectEntity tse = new TypeSelectEntity();

        StringBuffer listOp = new StringBuffer();
        StringBuffer finishedProOp = new StringBuffer();
        StringBuffer doingProOp = new StringBuffer();
        List<AppTypeDetailEntity> finishedList = new ArrayList<>();
        List<AppTypeDetailEntity> doingList = new ArrayList<>();
        String selectedTypeName;
        String selectedTypeId1;

        AppTypeInfoEntity infoEntity = infoDao.findByTypeCode(typeCode);
        if(infoEntity == null){
            listOp.append("<option value''>--暂无分类--</option>");
            doingProOp.append("<li role='resentation' onclick = \"click_type('").append("00000000-0000-0000-000000000000").append("',this);\" ><a href='javascript:void(0)'>暂无分类</a></li>");
            finishedProOp.append("<li role='resentation' onclick = \"click_type('").append("00000000-0000-0000-000000000000").append("',this);\" ><a href='javascript:void(0)'>暂无分类</a></li>");
        }else {
            List<AppTypeDetailEntity> list = detialdao.findByTypeDetailId(infoEntity.getTypeId().toString());
            if(list == null || list.size() < 1){
                listOp.append("<option value=''>--暂无分类--</option>");
                doingProOp.append("<li role='resentation' onclick = \"click_type('").append("00000000-0000-0000-000000000000").append("',this);\" ><a href='javascript:void(0)'>暂无分类</a></li>");
                finishedProOp.append("<li role='resentation' onclick = \"click_type('").append("00000000-0000-0000-000000000000").append("',this);\" ><a href='javascript:void(0)'>暂无分类</a></li>");
            }else{
                listOp.append("<option value=''>--请选择--</option>");
                int i=0;
                for(AppTypeDetailEntity m:list){
                    String selected="";
                    String cls="";
                    if((StringUtils.isNotBlank(selectedTypeId) && selectedTypeId.equals(m.getTypeDetailId())) || (i==0 && (StringUtils.isBlank(selectedTypeId) || selectedTypeId.equals("00000000-0000-0000-000000000000")) )){
                        selected="selected";
                        cls="active";
                        tse.setSelectedTypeId(m.getTypeDetailId());
                        tse.setSelectedTypeName(m.getDetailName());
                    }
                    listOp.append("<option "+selected+" value='"+m.getTypeDetailId()+"' data-val='"+m.getDetailValue()+"'>"+m.getDetailName()+"</option>");
                    if (m.isValid()){
                        doingProOp.append("<li class='"+cls+"' role='resentation' onclick = \"click_type('").append(m.getTypeDetailId()).append("',this);\" ><a href='javascript:void(0)'>").append(m.getDetailName()).append("</a></li>");
                        doingList.add(m);
                    } else{
                        finishedProOp.append("<li class='"+cls+"' role='presentation' onclick = \"click_type('").append(m.getTypeDetailId()).append("',this);\" ><a href='javascript:void(0)'>").append(m.getDetailName()).append("</a></li>");
                        finishedList.add(m);
                    }
                    i++;
                }
            }
        }
        tse.setListOp(listOp);
        tse.setFinishedProOp(finishedProOp);
        tse.setDoingProOp(doingProOp);
        tse.setFinishedList(finishedList);
        tse.setDoingList(doingList);
        return tse;
    }

    //根据上级，得到子级下一个排序的数据
    public int getLevelByMainId(String typeId){
        String sql = "select max(a.DETAIL_LEVEL) from app_type_detail a where a.TYPE_ID=:typeId";
        List list = entityManager.createNativeQuery(sql).setParameter("typeId",typeId).getResultList();
        int n = 1;
        if(list != null && list.size()>0 && list.get(0)!=null){
            n = Integer.parseInt(list.get(0).toString()) + 1;
        }
        return n;
    }












































}
