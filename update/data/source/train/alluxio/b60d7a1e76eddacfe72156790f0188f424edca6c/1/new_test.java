@Test
  public void setGroupBitsTest() {
    Mode mode = new Mode((short) 0640);
    mode.setGroupBits(Mode.Bits.READ_EXECUTE);
    Assert.assertEquals(Mode.Bits.READ_EXECUTE, mode.getGroupBits());
    mode.setGroupBits(Mode.Bits.WRITE);
    Assert.assertEquals(Mode.Bits.WRITE, mode.getGroupBits());
    mode.setGroupBits(Mode.Bits.ALL);
    Assert.assertEquals(Mode.Bits.ALL, mode.getGroupBits());
  }