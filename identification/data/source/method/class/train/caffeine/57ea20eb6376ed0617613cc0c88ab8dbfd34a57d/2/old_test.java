  @Test(dataProvider = "full")
  public void concat(LinkedDeque<LinkedValue> deque) {
    List<LinkedValue> expect = ImmutableList.copyOf(
        Iterators.concat(deque.iterator(), deque.descendingIterator()));
    Iterable<LinkedValue> actual = () -> PeekingIterator.concat(
        deque.iterator(), deque.descendingIterator());
    assertThat(actual, contains(expect.toArray(new LinkedValue[0])));
  }