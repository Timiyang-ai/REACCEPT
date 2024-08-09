@Test
  public void testAssign() {
    executor.assign("foo", "2", message, Context.EMPTY_CONTEXT());

    // verify
    Object var = executor.getState().get("foo");
    assertThat(var, instanceOf(Integer.class));
    assertThat(var, equalTo(2));
  }