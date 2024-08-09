@Test
  public void saveCTLSchemaTest() throws Exception {
    this.loginKaaAdmin();
    CtlSchemaDto beta = client.saveCTLSchemaWithAppToken(getResourceAsString(TEST_CTL_SCHEMA_BETA), null, null);
    Assert.assertNotNull(beta.getId());

    this.loginTenantDeveloper(tenantDeveloperUser);
    CtlSchemaDto alpha = client.saveCTLSchemaWithAppToken(getResourceAsString(TEST_CTL_SCHEMA_ALPHA), tenantDeveloperDto.getTenantId(), null);
    Assert.assertNotNull(alpha.getId());
  }