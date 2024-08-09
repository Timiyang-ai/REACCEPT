public static Date string2Date(String dateString,String datePattern){
        return DateFormatUtil.parse(dateString, datePattern);
    }