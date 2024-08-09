  @Test
  public void createNoAccess() {
    Mode mode = Mode.createNoAccess();
    assertEquals(Mode.Bits.NONE, mode.getOwnerBits());
    assertEquals(Mode.Bits.NONE, mode.getGroupBits());
    assertEquals(Mode.Bits.NONE, mode.getOtherBits());
    assertEquals(0000, mode.toShort());
  }