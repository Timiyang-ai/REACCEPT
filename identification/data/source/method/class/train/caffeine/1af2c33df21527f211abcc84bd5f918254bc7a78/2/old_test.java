  @Test(dataProvider = "empty", expectedExceptions = NoSuchElementException.class)
  public void getLast_whenEmpty(LinkedDeque<LinkedValue> deque) {
    deque.getLast();
  }