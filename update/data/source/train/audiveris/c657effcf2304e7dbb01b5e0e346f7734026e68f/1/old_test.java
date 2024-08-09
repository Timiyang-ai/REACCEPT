@Test
    public void parseSample() {
        String json = "[1001, 159, 1005, 163, \"articStaccatoBelow\"]";
        JSONArray array = new JSONArray(json);
        Object sample = DataParser.parseSample(array);
        // TODO: Make a class for the parsed samples.
    }