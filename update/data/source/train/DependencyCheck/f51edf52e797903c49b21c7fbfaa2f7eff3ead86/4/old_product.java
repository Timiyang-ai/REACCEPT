public String csvCpeConfidence(Set<Identifier> ids) {
        if (ids == null || ids.isEmpty()) {
            return "";
        }
        boolean addComma = false;
        final StringBuilder sb = new StringBuilder();
        for (Identifier id : ids) {
            if ("cpe".equals(id.getType())) {
                if (addComma) {
                    sb.append(", ");
                } else {
                    addComma = true;
                }
                sb.append(id.getConfidence());
            }
        }
        return StringEscapeUtils.escapeCsv(sb.toString());
    }