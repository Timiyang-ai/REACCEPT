protected UserDto createUser(org.kaaproject.kaa.common.dto.admin.UserDto tenant, KaaAuthorityDto authority) throws Exception {
    UserDto user = new UserDto();
    String username = generateString(USERNAME);
    user.setUsername(username);
    user.setMail(username + "@demoproject.org");
    user.setFirstName(generateString("Test"));
    user.setLastName(generateString("User"));
    user.setAuthority(authority);
    if (tenant == null) {
      tenant = createTenantAdmin();
    }
    user.setTenantId(tenantAdminDto.getTenantId());
    loginTenantAdmin(tenant.getUsername());
    UserDto savedUser = client.editUser(user);
    return savedUser;
  }