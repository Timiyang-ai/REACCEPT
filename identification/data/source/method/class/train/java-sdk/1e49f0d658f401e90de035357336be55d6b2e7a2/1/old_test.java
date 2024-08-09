@Test
    public void testDeserialize() {
        String[] dateStrings = {
            "2016-06-20T04:25:16.218+0000",
            "2016-06-20T04:25:16",
            "2016-06-20T04:25:16.218Z",
            "2015-05-28T18:01:57Z",
            "2016-06-20T04:25:16.218+0000"
        };

        String jsonStr = "[\"" + StringUtils.join(dateStrings, "\",\"") + "\"]";
        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(jsonStr);
        DateDeserializer deserializer = new DateDeserializer();
        for (int i = 0; i < 5; i++) {
          System.out.println(deserializer.deserialize(element.getAsJsonArray().get(i), null, null));
            assertTrue(deserializer.deserialize(element.getAsJsonArray().get(i), null, null) != null);
        }
    }