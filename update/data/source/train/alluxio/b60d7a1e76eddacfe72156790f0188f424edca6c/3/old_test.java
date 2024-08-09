@Test
  public void applyExtraOwnerBitsTest() {
    Mode mode = new Mode((short) 0640);
    mode.applyExtraOwnerBits(Mode.Bits.READ_EXECUTE);
    Assert.assertEquals(Mode.Bits.ALL, mode.getOwnerBits());
    mode.applyExtraOwnerBits(Mode.Bits.READ);
    Assert.assertEquals(Mode.Bits.ALL, mode.getOwnerBits());
  }