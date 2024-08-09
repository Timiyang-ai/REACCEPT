@Test
    public void test_putFilename() {
        DBObject properties = new BasicDBObject();
        String expectedFilename = "myFilename";
        BodyInjectorHandler.putFilename(expectedFilename, "defaultFilename", properties);
        assertEquals(expectedFilename, properties.get(BodyInjectorHandler.FILENAME));
    }