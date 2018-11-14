package net.xinqushi.wechat.common.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
*@author wangwei
*@created 0:50 2018/8/23
 *@classname EmptyUtil
*@classdescription  判空工具类
*
*/
public class EmptyUtil {
    /**
     *
     * @param collection
     * @return 判断集合是否为空
     */
    public static boolean CollectionIsEmpty(Collection collection){

        if(null==collection||collection.isEmpty()){
            return true;
        }
        return false;
    }

}
