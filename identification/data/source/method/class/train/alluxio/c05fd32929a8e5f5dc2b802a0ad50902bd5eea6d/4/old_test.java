  @Test
  public void setMode() {
    AccessControlList acl = new AccessControlList();
    short mode = new Mode(Mode.Bits.EXECUTE, Mode.Bits.WRITE, Mode.Bits.READ).toShort();
    acl.setMode(mode);
    assertEquals(mode, acl.getMode());
  }