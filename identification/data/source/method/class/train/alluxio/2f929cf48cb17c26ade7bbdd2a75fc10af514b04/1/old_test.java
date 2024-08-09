@Test
  public void applyUMaskTest() {
    Mode umaskPermission = new Mode((short) 0022);
    Permission permission =
        new Permission("user1", "group1", Mode.getDefault());
    permission.applyUMask(umaskPermission);

    Assert.assertEquals(Mode.Bits.ALL, permission.getMode().getUserMode());
    Assert.assertEquals(Mode.Bits.READ_EXECUTE,
        permission.getMode().getGroupMode());
    Assert.assertEquals(Mode.Bits.READ_EXECUTE,
        permission.getMode().getOtherMode());
    verifyPermissionStatus("user1", "group1", (short) 0755, permission);
  }