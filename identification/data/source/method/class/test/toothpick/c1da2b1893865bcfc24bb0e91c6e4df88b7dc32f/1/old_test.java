  @Test
  public void reset_shouldCallResetForProvidedScope() {
    // GIVEN
    ScopeNode mockScope = mock(ScopeNode.class);
    mockScope.reset();

    // WHEN
    Toothpick.reset(mockScope);

    // THEN
    verify(mockScope);
  }