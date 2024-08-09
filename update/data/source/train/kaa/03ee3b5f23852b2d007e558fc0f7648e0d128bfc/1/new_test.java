@Test
  public void testTenantAdminCreateOtherUser() throws Exception {
    loginTenantAdmin(tenantAdminDto.getUsername());
    UserDto user = createUser(KaaAuthorityDto.TENANT_DEVELOPER);

    UserDto storedUser = client.getUser(user.getId());
    Assert.assertEquals(storedUser.getAuthority(), KaaAuthorityDto.TENANT_DEVELOPER);
    Assert.assertNotNull(storedUser);
    assertUsersEquals(user, storedUser);
    Assert.assertEquals(tenantAdminDto.getTenantId(), storedUser.getTenantId());
  }