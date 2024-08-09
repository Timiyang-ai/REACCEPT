public String csv(String text) {
        if (text == null || text.isEmpty()) {
            return "\"\"";
        }
        return StringEscapeUtils.escapeCsv(text.trim().replace("\n", " "));
    }