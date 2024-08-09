@Test
  public void testMakeVersionInfoBits() throws WriterException {
    // From Appendix D in JISX0510:2004 (p 68)
    BitArray bits = new BitArray();
    MatrixUtil.makeVersionInfoBits(7, bits);
    assertEquals(" ...XXXXX ..X..X.X ..", bits.toString());
  }