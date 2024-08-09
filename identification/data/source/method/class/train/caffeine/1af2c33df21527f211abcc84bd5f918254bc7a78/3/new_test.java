  @Test(dataProvider = "empty")
  public void addFirst_whenEmpty(LinkedDeque<LinkedValue> deque) {
    LinkedValue value = new LinkedValue(1);
    deque.addFirst(value);
    assertThat(deque.peekFirst(), is(value));
    assertThat(deque.peekLast(), is(value));
    assertThat(deque, hasSize(1));
  }