@Test
    public void deleteCTLSchemaByFqnAndVersionTest() throws Exception {
        this.loginTenantDeveloper(tenantDeveloperUser);
        final CTLSchemaInfoDto saved = this.createCTLSchema(this.ctlRandomFieldType(), CTL_DEFAULT_NAMESPACE, 1, CTLSchemaScopeDto.TENANT, null, null, null);
        client.deleteCTLSchemaByFqnAndVersion(saved.getFqn(), saved.getVersion());
        this.checkNotFound(new TestRestCall() {
            @Override
            public void executeRestCall() throws Exception {
                client.getCTLSchemaByFqnAndVersion(saved.getFqn(), saved.getVersion()).toString();
            }
        });
    }