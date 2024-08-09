public static ObjectNode toJson(InputStream stream) {
        ObjectNode response = null;
        try {
            response = readTreeFromStream(MAPPER, stream);
        } catch (IOException e) {
            log.error("Parse json string failed {}", e.getMessage());
        }

        return response;
    }