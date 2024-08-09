  @Test
  public void getPermission() {
    AccessControlList acl = new AccessControlList();
    setPermissions(acl);

    assertMode(Mode.Bits.ALL, acl, OWNING_USER, Collections.emptyList());
    assertMode(Mode.Bits.READ_EXECUTE, acl, NAMED_USER, Collections.emptyList());
    assertMode(Mode.Bits.READ_EXECUTE, acl, OTHER_USER, Lists.newArrayList(OWNING_GROUP));
    assertMode(Mode.Bits.READ, acl, OTHER_USER, Lists.newArrayList(NAMED_GROUP));
    assertMode(Mode.Bits.WRITE_EXECUTE, acl, OTHER_USER, Lists.newArrayList(NAMED_GROUP2));
    assertMode(Mode.Bits.ALL, acl, OTHER_USER, Lists.newArrayList(NAMED_GROUP, NAMED_GROUP2));
    assertMode(Mode.Bits.EXECUTE, acl, OTHER_USER, Collections.emptyList());
    assertMode(Mode.Bits.EXECUTE, acl, OTHER_USER, Lists.newArrayList(OTHER_GROUP));
  }