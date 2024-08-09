  @Test(dataProvider = "empty")
  public void peek_whenEmpty(LinkedDeque<LinkedValue> deque) {
    assertThat(deque.peek(), is(nullValue()));
  }