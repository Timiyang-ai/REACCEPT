  @Test(dataProvider = "full")
  public void moveToBack_first(LinkedDeque<LinkedValue> deque) {
    checkMoveToBack(deque, deque.getFirst());
  }