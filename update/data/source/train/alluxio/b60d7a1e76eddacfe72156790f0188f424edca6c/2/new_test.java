@Test
  public void setOtherBitsTest() {
    Mode mode = new Mode((short) 0640);
    mode.setOtherBits(Mode.Bits.READ_EXECUTE);
    Assert.assertEquals(Mode.Bits.READ_EXECUTE, mode.getOtherBits());
    mode.setOtherBits(Mode.Bits.WRITE);
    Assert.assertEquals(Mode.Bits.WRITE, mode.getOtherBits());
    mode.setOtherBits(Mode.Bits.ALL);
    Assert.assertEquals(Mode.Bits.ALL, mode.getOtherBits());
  }