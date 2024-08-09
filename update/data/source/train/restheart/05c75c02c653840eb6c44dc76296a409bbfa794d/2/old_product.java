protected static DBObject extractProperties(final FormData formData) throws JSONParseException {
        DBObject properties = new BasicDBObject();

        final String propsString = formData.getFirst(PROPERTIES) != null
                ? formData.getFirst(PROPERTIES).getValue()
                : null;

        if (propsString != null) {
            properties = (DBObject) JSON.parse(propsString);
        }

        return properties;
    }