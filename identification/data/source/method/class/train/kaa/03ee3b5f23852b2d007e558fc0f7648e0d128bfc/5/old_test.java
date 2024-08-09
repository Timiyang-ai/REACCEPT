@Test
    public void testGetTenant() throws Exception {
        TenantUserDto tenant = createTenant();
        
        TenantUserDto storedTenant = client.getTenant(tenant.getId());
        
        Assert.assertNotNull(storedTenant);
        assertTenantsEquals(tenant, storedTenant);
        client.deleteTenant(tenant.getId());
    }