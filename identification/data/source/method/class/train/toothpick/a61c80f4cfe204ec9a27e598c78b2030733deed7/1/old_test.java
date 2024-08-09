  @Test
  public void closeScope_shouldMarkThisScopeAsClosed() {
    // GIVEN
    ScopeImpl scope = (ScopeImpl) Toothpick.openScope("foo");

    // WHEN
    Toothpick.closeScope("foo");

    // THEN
    assertThat(scope.isOpen, is(false));
  }