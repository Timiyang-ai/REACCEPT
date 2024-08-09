  @Test
  public void create_throwsExceptionWhenLabelIsNotLowercase() {
    Exception e =
        assertThrows(
            IllegalArgumentException.class,
            () ->
                ReservedList.create(
                    "UPPER.tld",
                    true,
                    ImmutableMap.of("UPPER", ReservedEntry.create(FULLY_BLOCKED, ""))));
    assertThat(e)
        .hasMessageThat()
        .contains("Label(s) [UPPER] must be in puny-coded, lower-case form");
  }