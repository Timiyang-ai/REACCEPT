@Test public void response_tagsNothingOnError() {
    parser.response(response, new RuntimeException("drat"), customizer);

    verifyNoMoreInteractions(response, customizer);
  }