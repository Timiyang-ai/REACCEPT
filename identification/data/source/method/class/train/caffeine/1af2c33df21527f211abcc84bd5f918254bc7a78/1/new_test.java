  @Test(dataProvider = "full")
  public void moveToFront_first(LinkedDeque<LinkedValue> deque) {
    checkMoveToFront(deque, deque.getFirst());
  }