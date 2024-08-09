public static String formatDate(Date date) {
        Calendar c = Calendar.getInstance();
        if (date == null) {
            return "null";
        }
        c.setTime(date);
        return String.format("%02d/%02d/%04d", c.get(Calendar.MONTH) + 1, c.get(Calendar.DATE), c.get(Calendar.YEAR));
    }