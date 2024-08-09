public String csvCpeConfidence(Set<Identifier> ids) {
        if (ids == null || ids.isEmpty()) {
            return "\"\"";
        }
        boolean addComma = false;
        final StringBuilder sb = new StringBuilder();
        for (Identifier id : ids) {
            if (addComma) {
                sb.append(", ");
            } else {
                addComma = true;
            }
            sb.append(id.getConfidence());
        }
        if (sb.length() == 0) {
            return "\"\"";
        }
        return StringEscapeUtils.escapeCsv(sb.toString());
    }