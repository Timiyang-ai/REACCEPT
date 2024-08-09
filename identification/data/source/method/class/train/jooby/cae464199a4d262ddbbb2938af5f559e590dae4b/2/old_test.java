  @Test
  public void toOptional() {
    /** Array: */
    queryString("a=1;a=2", queryString -> {
      assertMessage(BadRequestException.class, () -> queryString.get("a").toOptional(),
          "Cannot convert value: 'a', to: 'java.lang.String'");
      assertEquals(Optional.of("1"), queryString.get("a").get(0).toOptional());
      assertEquals(Optional.empty(), queryString.get("a").get(2).toOptional());
    });
  }