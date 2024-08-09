protected JsonObject readJsonFromContent(Object contentToValidate) throws Exception {
        if (contentToValidate == null || !(contentToValidate instanceof String)) {
            String className = (contentToValidate == null) ? null : contentToValidate.getClass().getName();
            throw new Exception("Provided content is not a string as expected so cannot be validated. The content is of type " + className + ".");
        }
        JsonObject obj = null;
        try {
            obj = Json.createReader(new StringReader((String) contentToValidate)).readObject();
        } catch (Exception e) {
            throw new Exception("Failed to read JSON data from the provided content. The exception was [" + e + "]. The content to validate was: [" + contentToValidate + "].");
        }
        return obj;
    }