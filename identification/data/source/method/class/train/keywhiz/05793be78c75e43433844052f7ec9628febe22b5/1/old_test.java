  @Test public void secretContents_empty() throws Exception {
    // No error expected when the list of requested secrets is empty
    SecretContentsResponseV2 resp = contents(SecretContentsRequestV2.fromParts(ImmutableSet.of()));
    assertThat(resp.successSecrets().isEmpty()).isTrue();
    assertThat(resp.missingSecrets().isEmpty()).isTrue();
  }