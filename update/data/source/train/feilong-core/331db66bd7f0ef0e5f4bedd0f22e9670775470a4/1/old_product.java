public static Date toDate(String dateString,String datePattern){
        return DateFormatUtil.parse(dateString, datePattern);
    }