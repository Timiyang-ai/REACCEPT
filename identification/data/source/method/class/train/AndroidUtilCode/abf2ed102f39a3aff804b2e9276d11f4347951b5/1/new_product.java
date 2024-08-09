public static String getChineseZodiac(final int year) {
        return CHINESE_ZODIAC[year % 12];
    }