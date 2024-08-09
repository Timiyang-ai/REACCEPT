@Test
  public void applyExtraOtherBitsTest() {
    Mode mode = new Mode((short) 0640);
    mode.applyExtraOtherBits(Mode.Bits.READ);
    Assert.assertEquals(Mode.Bits.READ, mode.getOtherBits());
    mode.applyExtraOtherBits(Mode.Bits.EXECUTE);
    Assert.assertEquals(Mode.Bits.READ_EXECUTE, mode.getOtherBits());
    mode.applyExtraOtherBits(Mode.Bits.WRITE_EXECUTE);
    Assert.assertEquals(Mode.Bits.ALL, mode.getOtherBits());
  }