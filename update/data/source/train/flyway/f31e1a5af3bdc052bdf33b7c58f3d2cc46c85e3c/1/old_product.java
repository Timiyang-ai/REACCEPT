public void setCurrentSchema(Schema schema) {
        try {
            doSetCurrentSchema(schema);
        } catch (SQLException e) {
            throw new FlywayException("Error setting current schema to " + schema, e);
        }
    }