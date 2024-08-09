@Test
  public void testCheckRegistrarConsoleLogin_authedWithValidSession_doesNothing() throws Exception {
    when(session.getAttribute("clientId")).thenReturn("TheRegistrar");
    assertThat(sessionUtils.checkRegistrarConsoleLogin(req, jart)).isTrue();
    verify(session).getAttribute("clientId");
    verifyNoMoreInteractions(session);
  }