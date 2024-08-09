private static DBObject extractProperties(final FormData data) throws JSONParseException {
        DBObject properties = new BasicDBObject();

        final String propsString = data.getFirst(PROPERTIES) != null
                ? data.getFirst(PROPERTIES).getValue()
                : null;

        if (propsString != null) {
            properties = (DBObject) JSON.parse(propsString);
        }

        return properties;
    }