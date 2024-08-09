  @Test
  public void getBlacklistDurationMsFor_blacklist404() {
    InvalidResponseCodeException exception =
        new InvalidResponseCodeException(
            404, "Not Found", Collections.emptyMap(), new DataSpec(Uri.EMPTY));
    assertThat(getDefaultPolicyBlacklistOutputFor(exception))
        .isEqualTo(DefaultLoadErrorHandlingPolicy.DEFAULT_TRACK_BLACKLIST_MS);
  }