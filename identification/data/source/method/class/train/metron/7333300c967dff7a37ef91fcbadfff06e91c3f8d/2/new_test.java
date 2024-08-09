    private JSONObject parse(String message) throws Exception {
        List<JSONObject> result = regularExpressionsParser.parse(message.getBytes(
            StandardCharsets.UTF_8));
        if (result.size() > 0) {
            return result.get(0);
        }
        throw new Exception("Could not parse : " + message);
    }