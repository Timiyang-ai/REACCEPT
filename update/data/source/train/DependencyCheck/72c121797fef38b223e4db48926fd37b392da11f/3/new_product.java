public String csv(String text) {
        if (text == null || text.isEmpty()) {
            return "\"\"";
        }
        final String str = text.trim().replace("\n", " ");
        if (str.length()==0) {
            return "\"\"";
        }
        return StringEscapeUtils.escapeCsv(str);
    }