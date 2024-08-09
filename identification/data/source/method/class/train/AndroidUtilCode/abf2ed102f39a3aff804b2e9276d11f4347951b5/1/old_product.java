public static String getChineseZodiac(int year) {
        return CHINESE_ZODIAC[year % 12];
    }