protected static BsonDocument extractProperties(
            final FormData formData)
            throws JSONParseException {
        BsonDocument properties = new BsonDocument();

        final String propsString = formData.getFirst(PROPERTIES) != null
                ? formData.getFirst(PROPERTIES).getValue()
                : null;

        if (propsString != null) {
            properties = BsonDocument.parse(propsString);
        }

        return properties;
    }