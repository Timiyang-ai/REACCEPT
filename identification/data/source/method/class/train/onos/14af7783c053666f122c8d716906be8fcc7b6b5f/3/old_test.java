    @Test
    public void convertJsonToDataNode() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode compareNode = mapper.createObjectNode()
                .put("alpha", "abc")
                .put("beta", 123)
                .put("gamma", true);
        ResourceData resourceData = ParseUtils.convertJsonToDataNode("/xyz", compareNode);
        ObjectNode testNode = ParseUtils.convertDataNodeToJson(resourceData.resourceId(), resourceData.dataNode());
        assertEquals(testNode, compareNode);
    }