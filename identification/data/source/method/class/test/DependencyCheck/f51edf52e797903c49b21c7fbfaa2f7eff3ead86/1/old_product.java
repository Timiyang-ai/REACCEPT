public String csv(String text) {
        if (text == null || text.isEmpty()) {
            return text;
        }
        return StringEscapeUtils.escapeCsv(text.trim().replace("\n", " "));
    }