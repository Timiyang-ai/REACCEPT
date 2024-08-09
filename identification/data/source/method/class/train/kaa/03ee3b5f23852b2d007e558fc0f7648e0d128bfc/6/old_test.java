@Test
    public void testCreateTenant() throws Exception {
        TenantUserDto tenant = createTenant();
        Assert.assertFalse(strIsEmpty(tenant.getId()));
        client.deleteTenant(tenant.getId());
    }