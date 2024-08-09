@SuppressLint("DefaultLocale")
    public static String millis2FitTimeSpan(long millis) {
        if (millis < 0) {
            return "shouldn't be less than zero!";
        } else if (millis < ConstUtils.SEC) {
            return String.format("%d毫秒", millis);
        } else if (millis < ConstUtils.MIN) {
            return String.format("%d秒", millis / ConstUtils.SEC);
        } else if (millis < ConstUtils.HOUR) {
            return String.format("%d分", millis / ConstUtils.MIN);
        } else if (millis < ConstUtils.DAY) {
            return String.format("%d小时", millis / ConstUtils.HOUR);
        } else {
            return String.format("%d天", millis / ConstUtils.DAY);
        }
    }