@Test
    public void saveCTLSchemaTest() throws Exception {
        this.loginKaaAdmin();
        CTLSchemaDto beta = client.saveCTLSchema(getResourceAsString(TEST_CTL_SCHEMA_BETA), null, null);
        Assert.assertNotNull(beta.getId());

        this.loginTenantDeveloper(tenantDeveloperUser);
        CTLSchemaDto alpha = client.saveCTLSchema(getResourceAsString(TEST_CTL_SCHEMA_ALPHA), tenantDeveloperDto.getTenantId(), null);
        Assert.assertNotNull(alpha.getId());
    }