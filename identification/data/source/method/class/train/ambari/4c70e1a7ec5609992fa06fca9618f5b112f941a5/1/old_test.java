@Test
  public void testExecute() throws Exception {
    PowerMock.replay(m_action);
    replayAll();

    m_action.execute(null);

    // easymock verify
    verifyAll();
  }