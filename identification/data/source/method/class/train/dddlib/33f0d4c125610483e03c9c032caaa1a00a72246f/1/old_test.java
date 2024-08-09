@Test
    public void testSetParameters_Map() {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", "abc");
        params.put("created", new Date());
        instance.setParameters(params);
        assertEquals(MapParameters.create(params), instance.getParameters());
    }