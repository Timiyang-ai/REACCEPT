    @Test
    public void convertInputStreamToObjectNode() throws Exception {
        InputStream inputStream = IOUtils.toInputStream(testJson);
        ObjectNode testNode = ParseUtils.convertInputStreamToObjectNode(inputStream);
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode compareNode = mapper.createObjectNode()
                .put("alpha", "abc")
                .put("beta", 123)
                .put("gamma", true);
        assertEquals(testNode, compareNode);
    }