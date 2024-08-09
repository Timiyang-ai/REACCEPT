  @Test
  public void test_parse_one() {
    EnumNames<MockEnum> test = EnumNames.of(MockEnum.class);
    assertThat(test.parse("One")).isEqualTo(MockEnum.ONE);
    assertThat(test.parse("ONE")).isEqualTo(MockEnum.ONE);
    assertThat(test.parse("one")).isEqualTo(MockEnum.ONE);
  }