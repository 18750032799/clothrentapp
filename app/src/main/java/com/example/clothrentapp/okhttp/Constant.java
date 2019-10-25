package com.example.clothrentapp.okhttp;

public class Constant {

//    public static String BASE_URL = "http://172.20.10.3:8088/Demo2_war_exploded/";

    public static String BASE_URL = "http://192.168.0.105:8088/clothService";
//    public static String BASE_URL = "http://172.20.10.2:8080/clothService";
    /**
     * 登录
     *
     * @param phone
     * @param password
     */
    public static String user_login = BASE_URL + "/LoginServlet";
    /**
     * 查询所有用户
     */
    public static String user_select_all = BASE_URL + "/SelectUserServlet";
    /**
     * 上传图片
     */
    public static String upload_img = BASE_URL + "/UploadImageServlet";

}
