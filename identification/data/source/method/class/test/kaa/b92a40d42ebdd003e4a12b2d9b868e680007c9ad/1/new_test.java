@Test
    public void saveCTLSchemaTest() throws Exception {
        this.loginKaaAdmin();
        CTLSchemaDto beta = client.saveCTLSchemaWithAppToken(getResourceAsString(TEST_CTL_SCHEMA_BETA), null, null);
        Assert.assertNotNull(beta.getId());

        this.loginTenantDeveloper(tenantDeveloperUser);
        CTLSchemaDto alpha = client.saveCTLSchemaWithAppToken(getResourceAsString(TEST_CTL_SCHEMA_ALPHA), tenantDeveloperDto.getTenantId(), null);
        Assert.assertNotNull(alpha.getId());
    }