  @Test
  public void toShort() {
    Mode mode = new Mode(Mode.Bits.ALL, Mode.Bits.READ_EXECUTE, Mode.Bits.READ_EXECUTE);
    assertEquals(0755, mode.toShort());

    mode = Mode.defaults();
    assertEquals(0777, mode.toShort());

    mode = new Mode(Mode.Bits.READ_WRITE, Mode.Bits.READ, Mode.Bits.READ);
    assertEquals(0644, mode.toShort());
  }