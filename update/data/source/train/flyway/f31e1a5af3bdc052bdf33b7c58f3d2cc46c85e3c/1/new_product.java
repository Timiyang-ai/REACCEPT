public void changeCurrentSchemaTo(Schema schema) {
        if (schema.getName().equals(originalSchema) || !schema.exists()) {
            return;
        }

        try {
            doChangeCurrentSchemaTo(schema.toString());
        } catch (SQLException e) {
            throw new FlywayException("Error setting current schema to " + schema, e);
        }
    }