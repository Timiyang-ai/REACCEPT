  @Test(dataProvider = "full")
  public void comparing(LinkedDeque<LinkedValue> deque) {
    List<LinkedValue> expect = ImmutableList.copyOf(
        Iterators.concat(deque.iterator(), deque.descendingIterator()));
    PeekingIterator<LinkedValue> actual = PeekingIterator.comparing(
        deque.iterator(), deque.descendingIterator(), (a, b) -> 1);
    assertThat(actual.peek(), is(expect.get(0)));
    assertThat((Iterable<LinkedValue>) () -> actual, contains(expect.toArray(new LinkedValue[0])));
  }