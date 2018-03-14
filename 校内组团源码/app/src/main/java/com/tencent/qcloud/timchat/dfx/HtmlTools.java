package com.tencent.qcloud.timchat.dfx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wuchaochao on 2018/1/26.
 */

public class HtmlTools {

    /**
     * 查找__VIEWSTATE参数的值
     * @param html HTML文档
     * @return 返回
     */
    public static String findViewState(String html) {
        String res="";
        String pattern="<input type=\"hidden\" name=\"__VIEWSTATE\" value=\"(.*?)\" />";

        Pattern p=Pattern.compile(pattern);
        Matcher m=p.matcher(html);

        if(m.find()){
            res=m.group();
            res=res.substring(res.indexOf("value=\"")+7,res.lastIndexOf("\""));
        }
        return res;
    }
}
