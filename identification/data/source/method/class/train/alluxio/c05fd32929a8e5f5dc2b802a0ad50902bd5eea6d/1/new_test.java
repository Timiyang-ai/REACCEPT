  @Test
  public void getMode() {
    AccessControlList acl = new AccessControlList();
    assertEquals(0, acl.getMode());
    acl.setEntry(new AclEntry.Builder().setType(AclEntryType.OWNING_USER).setSubject(OWNING_USER)
        .addAction(AclAction.READ).build());
    acl.setEntry(new AclEntry.Builder().setType(AclEntryType.OWNING_GROUP).setSubject(OWNING_GROUP)
        .addAction(AclAction.WRITE).build());
    acl.setEntry(new AclEntry.Builder().setType(AclEntryType.OTHER)
        .addAction(AclAction.EXECUTE).build());
    assertEquals(new Mode(Mode.Bits.READ, Mode.Bits.WRITE, Mode.Bits.EXECUTE).toShort(),
        acl.getMode());
  }