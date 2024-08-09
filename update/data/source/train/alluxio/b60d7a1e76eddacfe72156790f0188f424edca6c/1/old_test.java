@Test
  public void applyExtraGroupBitsTest() {
    Mode mode = new Mode((short) 0640);
    mode.applyExtraGroupBits(Mode.Bits.READ_EXECUTE);
    Assert.assertEquals(Mode.Bits.READ_EXECUTE, mode.getGroupBits());
    mode.applyExtraGroupBits(Mode.Bits.WRITE);
    Assert.assertEquals(Mode.Bits.ALL, mode.getGroupBits());
  }