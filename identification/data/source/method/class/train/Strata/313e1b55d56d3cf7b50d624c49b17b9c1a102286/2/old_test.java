  @Test
  public void getValue() {
    MarketDataBox<Integer> box = MarketDataBox.ofScenarioValues(27, 28, 29);
    assertThat(box.getValue(0)).isEqualTo(27);
    assertThat(box.getValue(1)).isEqualTo(28);
    assertThat(box.getValue(2)).isEqualTo(29);
    assertThatIllegalArgumentException()
        .isThrownBy(() -> box.getValue(-1))
        .withMessage("Expected 0 <= 'scenarioIndex' < 3, but found -1");
    assertThatIllegalArgumentException()
        .isThrownBy(() -> box.getValue(3))
        .withMessage("Expected 0 <= 'scenarioIndex' < 3, but found 3");
  }