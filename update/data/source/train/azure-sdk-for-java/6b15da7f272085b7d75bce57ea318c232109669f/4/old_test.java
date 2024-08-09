@Test
    public void createSession() {
        // Arrange
        // We want to ensure that the ReactorExecutor does not shutdown unexpectedly. There are still items to still process.
        when(reactor.process()).thenReturn(true);
        when(reactor.connectionToHost(connectionHandler.getHostname(), connectionHandler.getProtocolPort(), connectionHandler)).thenReturn(connectionProtonJ);
        when(connectionProtonJ.session()).thenReturn(session);
        when(session.attachments()).thenReturn(record);

        // Act & Assert
        StepVerifier.create(connection.createSession(SESSION_NAME))
            .assertNext(s -> {
                Assert.assertNotNull(s);
                Assert.assertEquals(SESSION_NAME, s.getSessionName());
                Assert.assertTrue(s instanceof ReactorSession);
                Assert.assertSame(session, ((ReactorSession) s).session());
            }).verifyComplete();

        // Assert that the same instance is obtained and we don't get a new session with the same name.
        StepVerifier.create(connection.createSession(SESSION_NAME))
            .assertNext(s -> {
                Assert.assertNotNull(s);
                Assert.assertEquals(SESSION_NAME, s.getSessionName());
                Assert.assertTrue(s instanceof ReactorSession);
                Assert.assertSame(session, ((ReactorSession) s).session());
            }).verifyComplete();

        verify(record, Mockito.times(1)).set(Handler.class, Handler.class, sessionHandler);
    }