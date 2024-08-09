  @Test
  public void evaluate_binding() throws Exception {
    Binding binding = Binding.valueOf("@{a.b.c}", null, null);

    Value value = binding.evaluate(null, data(), 0);

    assertThat(value.getAsString(), is("10"));
  }