@Test
    public void testGetTenants() throws Exception {
        List<TenantUserDto> tenants  = new ArrayList<TenantUserDto>(10);
        for (int i=0;i<10;i++) {
            TenantUserDto tenant = createTenant();
            tenants.add(tenant);
        }
        
        Collections.sort(tenants, new IdComparator());
        
        List<TenantUserDto> storedTenants = client.getTenants();
        Collections.sort(storedTenants, new IdComparator());
        
        Assert.assertEquals(tenants.size(), storedTenants.size());
        for (int i=0;i<tenants.size();i++) {
            TenantUserDto tenant = tenants.get(i);
            TenantUserDto storedTenant = storedTenants.get(i);
            assertTenantsEquals(tenant, storedTenant);
        }
        for (int i=0;i<storedTenants.size();i++) {
            client.deleteTenant(storedTenants.get(i).getId());
        }
    }