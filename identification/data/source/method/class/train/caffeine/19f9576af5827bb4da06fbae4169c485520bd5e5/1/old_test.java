  @Test(dataProvider = "empty")
  public void offer_whenEmpty(Queue<Integer> queue) {
    assertThat(queue.offer(1), is(true));
    assertThat(queue, hasSize(1));
  }