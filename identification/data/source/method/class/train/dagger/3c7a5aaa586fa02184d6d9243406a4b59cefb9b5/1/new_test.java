  @Test public void resolveType() {
    final List<String> resolveAttempts = new ArrayList<String>();
    Elements elements = mock(Elements.class);
    when(elements.getTypeElement(any(CharSequence.class))).then(new Answer<TypeElement>() {
      @Override public TypeElement answer(InvocationOnMock invocationOnMock) throws Throwable {
        resolveAttempts.add(invocationOnMock.getArguments()[0].toString());
        return null;
      }
    });

    assertNull(GraphAnalysisLoader.resolveType(elements, "blah.blah.Foo$Bar$Baz"));
    List<String> expectedAttempts = ImmutableList.<String>builder()
        .add("blah.blah.Foo.Bar.Baz")
        .add("blah.blah.Foo.Bar$Baz")
        .add("blah.blah.Foo$Bar.Baz")
        .add("blah.blah.Foo$Bar$Baz")
        .build();
    assertEquals(expectedAttempts, resolveAttempts);

    resolveAttempts.clear();
    assertNull(GraphAnalysisLoader.resolveType(elements, "$$Foo$$Bar$$Baz$$"));
    expectedAttempts = ImmutableList.<String>builder()
        .add("$.Foo.$Bar.$Baz.$")
        .add("$.Foo.$Bar.$Baz$$")
        .add("$.Foo.$Bar$.Baz.$")
        .add("$.Foo.$Bar$.Baz$$")
        .add("$.Foo.$Bar$$Baz.$")
        .add("$.Foo.$Bar$$Baz$$")
        .add("$.Foo$.Bar.$Baz.$")
        .add("$.Foo$.Bar.$Baz$$")
        .add("$.Foo$.Bar$.Baz.$")
        .add("$.Foo$.Bar$.Baz$$")
        .add("$.Foo$.Bar$$Baz.$")
        .add("$.Foo$.Bar$$Baz$$")
        .add("$.Foo$$Bar.$Baz.$")
        .add("$.Foo$$Bar.$Baz$$")
        .add("$.Foo$$Bar$.Baz.$")
        .add("$.Foo$$Bar$.Baz$$")
        .add("$.Foo$$Bar$$Baz.$")
        .add("$.Foo$$Bar$$Baz$$")
        .add("$$Foo.$Bar.$Baz.$")
        .add("$$Foo.$Bar.$Baz$$")
        .add("$$Foo.$Bar$.Baz.$")
        .add("$$Foo.$Bar$.Baz$$")
        .add("$$Foo.$Bar$$Baz.$")
        .add("$$Foo.$Bar$$Baz$$")
        .add("$$Foo$.Bar.$Baz.$")
        .add("$$Foo$.Bar.$Baz$$")
        .add("$$Foo$.Bar$.Baz.$")
        .add("$$Foo$.Bar$.Baz$$")
        .add("$$Foo$.Bar$$Baz.$")
        .add("$$Foo$.Bar$$Baz$$")
        .add("$$Foo$$Bar.$Baz.$")
        .add("$$Foo$$Bar.$Baz$$")
        .add("$$Foo$$Bar$.Baz.$")
        .add("$$Foo$$Bar$.Baz$$")
        .add("$$Foo$$Bar$$Baz.$")
        .add("$$Foo$$Bar$$Baz$$")
        .build();
    assertEquals(expectedAttempts, resolveAttempts);

    Mockito.validateMockitoUsage();
  }