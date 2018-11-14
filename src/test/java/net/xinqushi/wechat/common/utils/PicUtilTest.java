package net.xinqushi.wechat.common.utils;

import org.junit.Test;

import static org.junit.Assert.*;

public class PicUtilTest {

    @Test
    public void main() {
        String  str="x.jpg";
        System.out.println(str.lastIndexOf("."));
        System.out.println(str.substring(str.lastIndexOf(".")));
    }
}