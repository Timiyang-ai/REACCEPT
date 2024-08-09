public static boolean isBefore(String dateString,String whenDateString,String datePattern){
        Date before = string2Date(dateString, datePattern);
        return isBefore(before, whenDateString, datePattern);
    }