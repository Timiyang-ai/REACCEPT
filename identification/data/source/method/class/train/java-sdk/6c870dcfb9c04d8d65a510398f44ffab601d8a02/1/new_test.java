@Test
    public void testDeserialize() {
//        String[] dateStrings = {
//            "2016-06-20T04:25:16.218+0000",
//            "2016-06-20T04:25:16",
//            "2016-06-20T04:25:16.218Z",
//            "2015-05-28T18:01:57Z",
//            "2016-06-20T04:25:16.218+0000",
//            "1478097789",
//            "1478097789000"
//        };
        DateDeserializer deserializer = new DateDeserializer();
        JsonParser parser = new JsonParser();

        // Date with MS and 3 digit TZ
        try {
            String dateString = "2016-06-20T04:25:16.218+000";
            JsonElement element = parser.parse("\"" + dateString + "\"");
            // I have no idea what the actual value should be, so just check not null
            assertNotNull(deserializer.deserialize(element, null, null));
        } catch (Exception ex) {
            fail(ex.getMessage());
        }

        // Date without MS or TZ
        try {
            String dateString = "2016-06-20T04:25:16";
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            Date dateVal = dateFormat.parse(dateString);
            JsonElement element = parser.parse("\"" + dateString + "\"");
            assertEquals(deserializer.deserialize(element, null, null),dateVal);
        } catch (Exception ex) {
            fail(ex.getMessage());
        }

        // ISO 8601 date / time with MS and 'Z' TZ
        try {
            String dateString = "2016-06-20T04:25:16.218Z";
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");
            Date dateVal = dateFormat.parse(dateString);
            JsonElement element = parser.parse("\"" + dateString + "\"");
            assertEquals(deserializer.deserialize(element, null, null), dateVal);
        } catch (Exception ex) {
            fail(ex.getMessage());
        }

        // ISO 8601 date /time with 'Z' TZ but no MS
        try {
            String dateString = "2015-05-28T18:01:57Z";
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX");
            Date dateVal = dateFormat.parse(dateString);
            JsonElement element = parser.parse("\"" + dateString + "\"");
            assertEquals(deserializer.deserialize(element, null, null), dateVal);
        } catch (Exception ex) {
            fail(ex.getMessage());
        }

        // Date with MS but no TZ (SpeechToText)
        try {
            String dateString = "2016-09-30T16:51:47.558";
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
            Date dateVal = dateFormat.parse(dateString);
            JsonElement element = parser.parse("\"" + dateString + "\"");
            assertEquals(deserializer.deserialize(element, null, null), dateVal);
        } catch (Exception ex) {
            fail(ex.getMessage());
        }

        // Seconds since epoch
        try {
            String dateString = "1478097789";
            Date dateVal = new Date(Long.parseLong(dateString)*1000);
            JsonElement element = parser.parse("\"" + dateString + "\"");
            assertEquals(deserializer.deserialize(element, null, null), dateVal);
        } catch (Exception ex) {
            fail(ex.getMessage());
        }

        // MS since epoch
        try {
            String dateString = "1478097789000";
            Date dateVal = new Date(Long.parseLong(dateString));
            JsonElement element = parser.parse("\"" + dateString + "\"");
            assertEquals(deserializer.deserialize(element, null, null), dateVal);
        } catch (Exception ex) {
            fail(ex.getMessage());
        }
    }