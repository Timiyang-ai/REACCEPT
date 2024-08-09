    @Test
    public void test_extractProperties() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        final String jsonString
                = "{\"key1\": \"value1\", \"key2\": \"value2\"}";
        FormData formData = new FormData(1);
        Field field = PipedHttpHandler.class.getDeclaredField("PROPERTIES");
        field.setAccessible(true);

        formData.add(field.get(null).toString(), jsonString);
        BsonDocument result = BodyInjectorHandler.extractMetadata(formData);
        BsonDocument expected = BsonDocument.parse(jsonString);
        assertEquals(expected, result);
    }