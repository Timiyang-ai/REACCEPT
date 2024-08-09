@Test
  public void applyUMaskTest() {
    Mode umaskMode = new Mode((short) 0022);
    Permission permission = new Permission("user1", "group1", Mode.getDefault());
    permission.applyUMask(umaskMode);

    Assert.assertEquals(Mode.Bits.ALL, permission.getMode().getUserBits());
    Assert.assertEquals(Mode.Bits.READ_EXECUTE, permission.getMode().getGroupBits());
    Assert.assertEquals(Mode.Bits.READ_EXECUTE, permission.getMode().getOtherBits());
    verifyPermission("user1", "group1", (short) 0755, permission);
  }