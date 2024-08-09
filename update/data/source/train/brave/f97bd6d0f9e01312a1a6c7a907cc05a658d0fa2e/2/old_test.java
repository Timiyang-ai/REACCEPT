@Test public void parseParentId_malformedReturnsFalse() {
    parseBadParentId("463acL$c9f6413ad");
    parseBadParentId("holy 💩");
    parseBadParentId("-");
    parseBadParentId("");

    assertThat(messages).containsExactly(
        "parent-id: 463acL$c9f6413ad is not a lower-hex string",
        "parent-id: holy 💩 is not a lower-hex string",
        "parent-id should be a 1 to 16 character lower-hex string with no prefix",
        "parent-id should be a 1 to 16 character lower-hex string with no prefix"
    );
  }