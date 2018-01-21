package com.example.wanhao.apiapp.config;

/**
 * Created by szh on 2017/5/14.
 * 常量字符串
 */

public class Constant {
    /**********************TYPE相关*******************************/
    public static final int  IMAGE_MESSAGE = 0;
    public static final int  NORMAL_MESSAGE = 1;

    /*********************请求相关********************************/
    //请求分页数据默认每页10条数据
    public static final int DEAULT_NUM_PER_PAGE = 10;
    //bean的格式
    public static final String  REQUEST_CODE = "code";
    public static final String  REQUEST_MSG = "msg";

    public static final String  REQUEST_DATA = "data";
    //不存在
    public static final String  SOMETHING_DONOT_EXIST = "-1";
    public static final String  SOMETHING_EXIST = "1";
    //请求成功和失败
    public static final String  REQUEST_SUCCESS = "200";
    public static final String  REQUEST_ERROR = "-1";
    //鉴权失败(账户可能被冻结)
    public static final String  OAUTH_FAILED = "400";
    /*********************请求相关********************************/

    /*********************文件命名相关********************************/
    public static final String  SHAREDPREFERENCES_DEFAULT_NAME = "img_classify_prefs";
    public static final String LOGIN_TOKEN = "token";
    /*********************文件命名相关********************************/
    /**********************Activity之间的通信************************/
    public static final String TASK_IMG_AMOUNT = "task_img_amount";
    public static final String TASK_ID = "task_id";

    /**********************ActivityForResult的请求码************************/
    public static final int CAMERA_CODE = 1;
    public static final int GALLERY_CODE = 2;
    public static final int CROP_CODE = 3;

    public static final String AVATAR_IMG_PATH = "imageclassify";

    /**********************任务是否提交标识************************/
    public static final String TASK_IS_COMMIT = "1";
    public static final String TASK_IS_NOT_COMMIT = "0";

    /**********************用户上传的头像的名称************************/
    public static final String USER_AVATAR_NAME = "avatar.jpg";

    /**********************用户选中贡献上传的图片保存目录************************/
    public static final String CONTRIBUTE_IMG_PATH = "contribute";
    public static final String CONTRIBUTE_IMG_FOLDER = "images";
    public static final String CONTRIBUTE_IMG_ZIP_PATH = "zip";
    public static final String CONTRIBUTE_IMG_ZIP_NAME = "contribute.zip";

    /**********************图片识别模型文件存放路径及名称************************/

}
