public static boolean isBefore(String dateBefore,String dateAfter,String datePattern){
        Date before = string2Date(dateBefore, datePattern);
        return isBefore(before, dateAfter, datePattern);
    }