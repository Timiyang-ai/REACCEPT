@Test
    public void deleteCTLSchemaByFqnAndVersionTest() throws Exception {
        this.loginTenantDeveloper(tenantDeveloperUser);
        final CTLSchemaDto saved = this.createCTLSchema(this.ctlRandomFieldType(), CTL_DEFAULT_NAMESPACE, 1, tenantDeveloperDto.getTenantId(), null, null, null);
        client.deleteCTLSchemaByFqnVersionTenantIdAndApplicationId(saved.getMetaInfo().getFqn(), saved.getVersion(), tenantDeveloperDto.getTenantId(), null);
        this.checkNotFound(new TestRestCall() {
            @Override
            public void executeRestCall() throws Exception {
                client.getCTLSchemaByFqnVersionTenantIdAndApplicationId(saved.getMetaInfo().getFqn(), saved.getVersion(), tenantDeveloperDto.getTenantId(), null);
            }
        });
    }