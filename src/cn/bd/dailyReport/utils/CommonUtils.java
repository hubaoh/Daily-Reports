package cn.bd.dailyReport.utils;

public class CommonUtils {

    /**
     * sql语句like查询值构造
     * @param param like查询匹配参数
     * @return String
     */
    public static String likePartten(String param){
        return "%" + param + "%";
    }

    /**
     * 获取文件后缀名(包含.)
     * @param fileName 文件名
     * @return String
     */
    public static String getFileSuffix(String fileName){
        return fileName.substring(fileName.lastIndexOf("."));
    }

    /**
     * 生成随机数
     * @param n：为随机数位数，大于0
     * @return Integer
     */
    public static Integer generateRandom(int n){
        int unit = (int)Math.pow(10, n - 1);
        return (int)(Math.random()*unit*9+unit);
    }

    /**
     * hex转化为byte[]
     * @param hex 待转换字符串
     * @return byte[]
     */
    public static byte[] hexStringToByteArray(String hex) {
        hex = hex.substring(2);
        int len = hex.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(hex.charAt(i), 16) << 4)
                    + Character.digit(hex.charAt(i+1), 16));
        }
        return data;
    }

}
