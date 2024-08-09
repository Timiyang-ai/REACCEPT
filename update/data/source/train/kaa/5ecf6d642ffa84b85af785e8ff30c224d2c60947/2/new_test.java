@Test
    public void getCTLSchemaByFqnAndVersionTest() throws Exception {
        this.loginTenantDeveloper(tenantDeveloperUser);
        CTLSchemaDto saved = this.createCTLSchema(this.ctlRandomFieldType(), CTL_DEFAULT_NAMESPACE, 1, tenantDeveloperDto.getTenantId(), null,  null, null);
        CTLSchemaDto loaded = client.getCTLSchemaByFqnVersionTenantIdAndApplicationId(saved.getMetaInfo().getFqn(), saved.getVersion(), tenantDeveloperDto.getTenantId(), null);
        Assert.assertNotNull(loaded);
        Assert.assertEquals(saved, loaded);
    }