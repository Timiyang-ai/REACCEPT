protected TenantDto createTenant() throws Exception {
    loginKaaAdmin();
    TenantDto tenantDto = new TenantDto();
    tenantDto.setName(generateString(TENANT_NAME));
    tenantDto = client.editTenant(tenantDto);
    return tenantDto;
  }