@Test
  public void getCTLSchemaByIdTest() throws Exception {
    this.loginTenantDeveloper(tenantDeveloperUser);
    CtlSchemaDto saved = this.createCTLSchema(this.ctlRandomFieldType(), CTL_DEFAULT_NAMESPACE, 1, tenantDeveloperDto.getTenantId(), null, null, null);
    CtlSchemaDto loaded = client.getCTLSchemaById(saved.getId());
    Assert.assertNotNull(loaded);
    Assert.assertEquals(saved, loaded);
  }