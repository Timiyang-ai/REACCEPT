@Test
  public void getCTLSchemaByIdTest() throws Exception {
    this.loginTenantDeveloper(tenantDeveloperUser);
    CTLSchemaDto saved = this.createCTLSchema(this.ctlRandomFieldType(), CTL_DEFAULT_NAMESPACE, 1, tenantDeveloperDto.getTenantId(), null, null, null);
    CTLSchemaDto loaded = client.getCTLSchemaById(saved.getId());
    Assert.assertNotNull(loaded);
    Assert.assertEquals(saved, loaded);
  }