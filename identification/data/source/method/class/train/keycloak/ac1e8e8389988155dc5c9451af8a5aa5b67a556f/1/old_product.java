protected String getJsonProperty(JsonNode jsonNode, String name) {
        if (jsonNode.has(name)) {
            return jsonNode.get(name).asText();
        }

        return null;
    }