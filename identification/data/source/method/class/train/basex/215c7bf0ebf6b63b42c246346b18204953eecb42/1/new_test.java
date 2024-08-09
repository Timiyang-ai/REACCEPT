@Test
  public final void testWriteNum() throws IOException {
    long pos = RANDOM_POS;
    da.cursor(pos);
    da.writeNum(CINT5);
    da.writeNum(CINT4);
    da.writeNum(CINT2);
    da.writeNum(CINT1);
    da.flush();

    assertContent(pos, CINT5_BIN);
    pos += CINT5_BIN.length;
    assertContent(pos, CINT4_BIN);
    pos += CINT4_BIN.length;
    assertContent(pos, CINT2_BIN);
    pos += CINT2_BIN.length;
    assertContent(pos, CINT1_BIN);
  }