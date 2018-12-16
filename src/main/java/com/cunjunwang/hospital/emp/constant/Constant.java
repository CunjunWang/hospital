package com.cunjunwang.hospital.emp.constant;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by CunjunWang on 2018/11/27.
 */
public class Constant {
    /** 默认日志打印Logger, 配置见logback logger节点 */
    public static final String LOGGER = "HOSPITAL";

    /** -------------------------------通用状态---------------------------------*/

    /** 有效 */
    public static final String VALID = "10011001";
    /** 无效 */
    public static final String INVALID = "10011002";
    /** 审核中 */
    public static final String UNAUDITED = "10021001";
    /** 审核通过 */
    public static final String APPROVED = "10021002";
    /** 审核未通过 */
    public static final String NOT_APPROVED = "10021003";
    /** 逻辑删除标识-已删除 */
    public static final Integer IS_DEL = 1;
    /** 逻辑删除标识-未删除 */
    public static final Integer NOT_DEL = 0;

    /** -------------------------------分页参数---------------------------------*/

    public static final String LAY_TABLE_SUCCESS = "1";

    public static final String LAY_TABLE_FAIL = "0";
    /** 默认开始页 */
    public static final int DEFAULT_START = 1;
    /** 默认分页长度 */
    public static final int DEFAULT_LENGTH = 20;
    /** 未查询到数据时的默认长度 */
    public static final int NO_RESULT = 0;

    /** -------------------------------日期格式化---------------------------------*/

    /** 线程不安全,建议使用DateUtils*/
    public static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    /** 线程不安全,建议使用DateUtils*/
    public static final DateFormat TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static final String FORMAT_DATE = "yyyy-MM-dd";
}
