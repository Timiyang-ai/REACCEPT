@Test
    public void test_putFilename() {
        BsonDocument properties = new BsonDocument();
        BsonString expectedFilename = new BsonString("myFilename");
        BodyInjectorHandler.putFilename(
                expectedFilename.getValue(),
                "defaultFilename", properties);

        assertEquals(
                expectedFilename,
                properties.get(BodyInjectorHandler.FILENAME));
    }