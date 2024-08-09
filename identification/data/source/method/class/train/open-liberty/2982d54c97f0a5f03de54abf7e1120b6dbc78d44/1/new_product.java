protected JsonObject readJsonFromContent(Object contentToValidate) throws Exception {
        if (contentToValidate == null) {
            throw new Exception("Provided content is null so cannot be validated.");
        }
        JsonObject obj = null;
        try {
            String responseText = WebResponseUtils.getResponseText(contentToValidate);
            obj = Json.createReader(new StringReader(responseText)).readObject();
        } catch (Exception e) {
            throw new Exception("Failed to read JSON data from the provided content. The exception was [" + e + "]. The content to validate was: [" + contentToValidate + "].");
        }
        return obj;
    }