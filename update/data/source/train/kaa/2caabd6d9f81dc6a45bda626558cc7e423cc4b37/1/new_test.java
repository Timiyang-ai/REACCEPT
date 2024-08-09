@Test
    public void getSystemCTLSchemaByFqnAndVersionTest() throws Exception {
        this.loginKaaAdmin();
        CTLSchemaDto saved = this.createCTLSchema(this.randomFieldType(), DEFAULT_NAMESPACE, 1, CTLSchemaScope.SYSTEM, null, null);
        this.loginTenantDeveloper(tenantDeveloperUser);
        CTLSchemaDto loaded = client.getCTLSchemaByFqnAndVersion(saved.getFqn(), saved.getVersion());
        Assert.assertNotNull(loaded);
        Assert.assertEquals(saved, loaded);
    }