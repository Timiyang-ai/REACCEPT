@Test
    public void saveCTLSchemaTest() throws Exception {
        this.loginKaaAdmin();
        CTLSchemaInfoDto beta = client.saveCTLSchema(getResourceAsString(TEST_CTL_SCHEMA_BETA));
        Assert.assertNotNull(beta.getId());

        this.loginTenantDeveloper(tenantDeveloperUser);
        CTLSchemaInfoDto alpha = client.saveCTLSchema(getResourceAsString(TEST_CTL_SCHEMA_ALPHA));
        Assert.assertNotNull(alpha.getId());
    }