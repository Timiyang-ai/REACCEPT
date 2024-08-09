    @Test
    public void convertObjectNodeToInputStream() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode compareNode = mapper.createObjectNode()
                .put("alpha", "abc")
                .put("beta", 123)
                .put("gamma", true);
        InputStream inputStream = ParseUtils.convertObjectNodeToInputStream(compareNode);
        String compareJson = IOUtils.toString(inputStream);
        assertEquals(testJson, compareJson);
    }