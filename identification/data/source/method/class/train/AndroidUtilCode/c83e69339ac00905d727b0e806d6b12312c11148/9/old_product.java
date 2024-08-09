@SuppressLint("DefaultLocale")
    public static String getFriendlyTimeSpanByNow(long millis) {
        long now = System.currentTimeMillis();
        long span = now - millis;
        if (span < 0)
            return String.format("%tc", millis);// U can read http://www.apihome.cn/api/java/Formatter.html to understand it.
        if (span < 1000) {
            return "刚刚";
        } else if (span < TimeConstant.MIN) {
            return String.format("%d秒前", span / TimeConstant.SEC);
        } else if (span < TimeConstant.HOUR) {
            return String.format("%d分钟前", span / TimeConstant.MIN);
        }
        // 获取当天00:00
        long wee = (now / TimeConstant.DAY) * TimeConstant.DAY - 8 * TimeConstant.HOUR;
        if (millis >= wee) {
            return String.format("今天%tR", millis);
        } else if (millis >= wee - TimeConstant.DAY) {
            return String.format("昨天%tR", millis);
        } else {
            return String.format("%tF", millis);
        }
    }