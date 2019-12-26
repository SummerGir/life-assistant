package util.dataManage;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * Controller中的通用方法
 */
public class GenericController {
    //将查询的数据转化成字符串返回
    public static String getTable(List<Map<String,Object>> list,int count,int page,int rows ){
        Map<String,Object> table = new HashMap();
        int total = count / rows;
        if(total == 0){
            total = 1;
        }else {
            if((count % rows) != 0){
                total++;
            }
        }
        table.put("page",page);
        table.put("records",count);
        table.put("tatol",total);
        table.put("rows",list);
        JSONObject jsonObject = JSONObject.fromObject(table);
        return jsonObject.toString();
    }

    public static JSONObject getWXParams(HttpServletRequest request){
        JSONObject jsonObject = null;
        StringBuilder stringBuilder = new StringBuilder();
        try {
           try(BufferedReader reader = request.getReader();) {
               char[] buff = new char[1024];
               int len;
               while ((len = reader.read(buff)) != -1){
                   stringBuilder.append(buff,0,len);
               }
               jsonObject = JSONObject.fromObject(stringBuilder.toString());
           }catch (Exception e){
               e.printStackTrace();
           }
        }catch (Exception e){
            e.printStackTrace();
        }
        return jsonObject;
    }

    //操作成功的返回
    public static ObjectNode returnSuccess(String msg){
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode objectNode = objectMapper.createObjectNode();
        if(StringUtils.isBlank(msg)){
            msg = "操作成功";
        }
        objectNode.put("error",0);
        objectNode.put("msg",msg);
        return objectNode;
    }

    //操作失败的返回
    public static ObjectNode returnFaild(String msg){
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode objectNode = objectMapper.createObjectNode();
        if(StringUtils.isBlank(msg)){
            msg = "操作失败";
        }
        objectNode.put("error",1);
        objectNode.put("msg",msg);
        return objectNode;
    }
}






















