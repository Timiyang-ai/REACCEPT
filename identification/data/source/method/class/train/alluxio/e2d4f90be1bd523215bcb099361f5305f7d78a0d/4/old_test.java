  @Test
  public void applyUMask() {
    String umask = "0022";
    Mode mode = ModeUtils.applyDirectoryUMask(Mode.defaults(), umask);
    assertEquals(Mode.Bits.ALL, mode.getOwnerBits());
    assertEquals(Mode.Bits.READ_EXECUTE, mode.getGroupBits());
    assertEquals(Mode.Bits.READ_EXECUTE, mode.getOtherBits());
    assertEquals(0755, mode.toShort());
  }