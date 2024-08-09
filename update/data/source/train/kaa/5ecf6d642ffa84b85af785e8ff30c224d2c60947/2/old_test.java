@Test
    public void getCTLSchemaByFqnAndVersionTest() throws Exception {
        this.loginTenantDeveloper(tenantDeveloperUser);
        CTLSchemaInfoDto saved = this.createCTLSchema(this.ctlRandomFieldType(), CTL_DEFAULT_NAMESPACE, 1, CTLSchemaScopeDto.TENANT, null,  null, null);
        CTLSchemaInfoDto loaded = client.getCTLSchemaByFqnAndVersion(saved.getFqn(), saved.getVersion());
        Assert.assertNotNull(loaded);
        Assert.assertEquals(saved, loaded);
    }