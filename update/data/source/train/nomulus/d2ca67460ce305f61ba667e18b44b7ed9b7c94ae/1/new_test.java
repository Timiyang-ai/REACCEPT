@Test
  public void getAllClientIdWithAccess_unauthorizedUser() {
    AuthenticatedRegistrarAccessor registrarAccessor =
        new AuthenticatedRegistrarAccessor(UNAUTHORIZED_USER, ADMIN_CLIENT_ID);

    assertThat(registrarAccessor.getAllClientIdWithRoles()).isEmpty();
  }