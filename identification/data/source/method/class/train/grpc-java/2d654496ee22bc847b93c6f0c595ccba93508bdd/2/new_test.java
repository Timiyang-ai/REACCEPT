  @Test
  public void minimum() {
    Deadline d1 = Deadline.after(1, TimeUnit.MINUTES, ticker);
    Deadline d2 = Deadline.after(2, TimeUnit.MINUTES, ticker);
    Deadline d3 = Deadline.after(3, TimeUnit.MINUTES, ticker);

    assertThat(d1.minimum(d2)).isSameInstanceAs(d1);
    assertThat(d2.minimum(d1)).isSameInstanceAs(d1);
    assertThat(d3.minimum(d2)).isSameInstanceAs(d2);
    assertThat(d2.minimum(d3)).isSameInstanceAs(d2);
  }