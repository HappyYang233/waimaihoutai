package com.example.springbootmybatis.entity.UtilEntity;

import com.example.springbootmybatis.util.TimerUtil;

public class Static {
    //下单标志位 0表示允许下单 1表示不允许下单
    public  static  int orderStatus = TimerUtil.getInitOrderStatus();
    //用餐类型  0表示午餐  1表示晚餐
    public static int orderType=TimerUtil.getInitOrderType();
    public static int num = 0;

    public static String Admin="{\n" +
            "    \"token\": \"sdasdasdasdasdasd\",\n" +
            "    \"left\": [\n" +
            "        {\n" +
            "            \"id\": 1,\n" +
            "            \"name\": \"订单管理\",\n" +
            "            \"children\": [\n" +
            "                {\n" +
            "                    \"id\": 11,\n" +
            "                    \"name\": \"订单列表\",\n" +
            "                    \"path\": \"/allOrder\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"id\": 12,\n" +
            "                    \"name\": \"当日订单\",\n" +
            "                    \"path\": \"/todayOrder\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"id\": 13,\n" +
            "                    \"name\": \"订单统计\",\n" +
            "                    \"path\": \"/statisticsOrder\"\n" +
            "                }\n" +
            "            ]\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\": 2,\n" +
            "            \"name\": \"菜品管理\",\n" +
            "            \"children\": [\n" +
            "                {\n" +
            "                    \"id\": 21,\n" +
            "                    \"name\": \"菜品列表\",\n" +
            "                    \"path\": \"/foodList\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"id\": 22,\n" +
            "                    \"name\": \"菜品分类\",\n" +
            "                    \"path\": \"/foodCategory\"\n" +
            "                }\n" +
            "            ]\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\": 3,\n" +
            "            \"name\": \"食堂信息管理\",\n" +
            "            \"children\": [\n" +
            "                {\n" +
            "                    \"id\": 31,\n" +
            "                    \"name\": \"地址管理\",\n" +
            "                    \"path\": \"/address\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"id\": 32,\n" +
            "                    \"name\": \"公告管理\",\n" +
            "                    \"path\": \"/notice\"\n" +
            "                }\n" +
            "            ]\n" +
            "        }\n" +
            "    ]\n" +
            "}";

    public static String SuperAdmin="{ \"token\": \"sdasdasdasdasdasd\",\n" +
            "\"left\": [\n" +
            "  {\n" +
            "    \"id\": 3,\n" +
            "    \"name\": \"食堂信息管理\",\n" +
            "    \"children\": [\n" +
            "      {\n" +
            "        \"id\": 33,\n" +
            "        \"name\": \"基本信息管理\",\n" +
            "        \"path\": \"/resInfo\"\n" +
            "      }\n" +
            "    ]\n" +
            "  },\n" +
            "   {\n" +
            "    \"id\": 4,\n" +
            "    \"name\": \"用户管理\",\n" +
            "    \"children\": [\n" +
            "      {\n" +
            "        \"id\": 41,\n" +
            "        \"name\": \"普通用户\",\n" +
            "        \"path\": \"/users\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"id\": 42,\n" +
            "        \"name\": \"食堂用户\",\n" +
            "        \"path\": \"/admin\"\n" +
            "      }\n" +
            "    ]\n" +
            "  }\n" +
            "   \n" +
            "]\n" +
            "}";
}
