public String csvGav(Set<Identifier> ids) {
        if (ids == null || ids.isEmpty()) {
            return "\"\"";
        }
        boolean addComma = false;
        final StringBuilder sb = new StringBuilder();
        for (Identifier id : ids) {
            if ("maven".equals(id.getType()) || "npm".equals(id.getType())) {
                if (addComma) {
                    sb.append(", ");
                } else {
                    addComma = true;
                }
                sb.append(id.getValue());
            }
        }
        return StringEscapeUtils.escapeCsv(sb.toString());
    }