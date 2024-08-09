@Test public void response_missingErrorMessage_tagsNothing() {
    parser.response(response, new RuntimeException("drat"), customizer);

    verify(response).errorMessage();
    verifyNoMoreInteractions(response, customizer);
  }