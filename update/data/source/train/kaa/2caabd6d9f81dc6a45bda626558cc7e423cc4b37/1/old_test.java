@Test
    public void getSystemCTLSchemas() throws Exception {
        CTLSchemaDto saved = this.createCTLSchema(null, null, CTLSchemaScope.SYSTEM);
        List<CTLSchemaDto> loaded = client.getSystemCTLSchemas();
        Assert.assertNotNull(loaded);
        Assert.assertEquals(1, loaded.size());
        Assert.assertEquals(saved, loaded.get(0));
    }