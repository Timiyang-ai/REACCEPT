  @Test
  public void test_put_by_index() throws Exception {
    SymbolicValue sv = new SymbolicValue();
    ProgramState ps = ProgramState.EMPTY_STATE;
    ProgramState ps2 = ps.put(2, sv);
    assertThat(ps).isNotSameAs(ps2);
    ProgramState ps3 = ps2.put(2, sv);
    assertThat(ps3).isSameAs(ps2);

  }