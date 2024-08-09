public static String getFirstValueFromQuery(Map<String, String[]> query, String key) {
        if (query == null) {
            return "";
        }

        String[] values = query.get(key);

        if (values != null && values.length > 0) {
           return values[0];
        } else {
            return "";
        }
    }