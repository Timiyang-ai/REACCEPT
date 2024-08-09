  @Test(dataProvider = "empty")
  public void isLast_whenEmpty(LinkedDeque<LinkedValue> deque) {
    assertThat(deque.isLast(new LinkedValue(0)), is(false));
    assertThat(deque.isLast(null), is(false));
  }