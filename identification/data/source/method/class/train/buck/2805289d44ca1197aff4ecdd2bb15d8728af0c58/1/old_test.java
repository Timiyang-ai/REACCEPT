  @Test
  public void replace() throws MacroException {
    ImmutableMap<String, String> replacements =
        ImmutableMap.of(
            "arg1", "something",
            "arg2", "something else");
    String actual =
        MacroFinder.replace(
            ImmutableMap.of(
                "macro", new FunctionMacroReplacer<>(args -> replacements.get(args.get(0)))),
            "hello $(macro arg1) goodbye $(macro arg2)",
            true,
            new StringMacroCombiner());
    assertEquals("hello something goodbye something else", actual);
  }