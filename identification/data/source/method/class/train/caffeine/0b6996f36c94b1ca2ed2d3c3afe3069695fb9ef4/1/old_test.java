  @Test(dataProvider = "empty")
  public void isFirst_whenEmpty(LinkedDeque<LinkedValue> deque) {
    assertThat(deque.isFirst(new LinkedValue(0)), is(false));
    assertThat(deque.isFirst(null), is(false));
  }