@Test
  public void setOwnerBitsTest() {
    Mode mode = new Mode((short) 0640);
    mode.setOwnerBits(Mode.Bits.READ_EXECUTE);
    Assert.assertEquals(Mode.Bits.READ_EXECUTE, mode.getOwnerBits());
    mode.setOwnerBits(Mode.Bits.WRITE);
    Assert.assertEquals(Mode.Bits.WRITE, mode.getOwnerBits());
    mode.setOwnerBits(Mode.Bits.ALL);
    Assert.assertEquals(Mode.Bits.ALL, mode.getOwnerBits());
  }