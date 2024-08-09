@Test(expected = AmbariException.class)
  public void testCreateUser_Duplicate() throws Exception {
    UserEntity existing = new UserEntity();
    existing.setUserName(SERVICEOP_USER_NAME);
    existing.setUserType(UserType.LDAP);
    existing.setUserId(1);
    existing.setMemberEntities(Collections.<MemberEntity>emptySet());
    PrincipalEntity principal = new PrincipalEntity();
    principal.setPrivileges(Collections.<PrivilegeEntity>emptySet());
    existing.setPrincipal(principal);
    initForCreateUser(existing);

    Users users = injector.getInstance(Users.class);
    users.createUser(SERVICEOP_USER_NAME, "qwert");
  }