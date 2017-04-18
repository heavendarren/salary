import com.yunxiang.salary.ReadExcelUtil;
import com.yunxiang.salary.SendMailUtil;

import javax.swing.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        //∂¡»°excel
        Map<String,List<String>> salaryMap= ReadExcelUtil.readSalary();
        Map<String,String> emailMap=ReadExcelUtil.readEmail();

        for(String userName:salaryMap.keySet()){
           List<String> salary=salaryMap.get(userName);
            String email=emailMap.get(userName);
            if(email==null){
                continue;
            }
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("username", userName);
            map.put("salary",salary);
            map.put("date",new Date());
            String templatePath = "salary.ftl";
            SendMailUtil.sendFtlMail(email, "sendemail test!", templatePath, map);
        }
    }
}
