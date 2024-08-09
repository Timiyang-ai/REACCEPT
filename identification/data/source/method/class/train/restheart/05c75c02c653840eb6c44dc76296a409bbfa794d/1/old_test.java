@Test
    public void test_extractProperties() {
        final String jsonString = "{\"key1\": \"value1\", \"key2\": \"value2\"}";
        FormData formData = new FormData(1);
        formData.add(BodyInjectorHandler.PROPERTIES, jsonString);
        DBObject result = BodyInjectorHandler.extractProperties(formData);
        DBObject expected = (DBObject) JSON.parse(jsonString);
        assertEquals(expected, result);
    }