@Test
    public void testDeleteTenant() throws Exception {
        final TenantUserDto tenant = createTenant();
        client.deleteTenant(tenant.getId());
        checkNotFound(new TestRestCall() {
            @Override
            public void executeRestCall() throws Exception {
                client.getTenant(tenant.getId());
            }
        });
   }