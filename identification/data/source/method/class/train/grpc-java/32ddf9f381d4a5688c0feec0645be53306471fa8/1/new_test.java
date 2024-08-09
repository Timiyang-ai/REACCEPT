  @Test
  public void withDeadlineAfter() {
    Deadline actual = CallOptions.DEFAULT.withDeadlineAfter(1, MINUTES).getDeadline();
    Deadline expected = Deadline.after(1, MINUTES);

    assertAbout(deadline()).that(actual).isWithin(10, MILLISECONDS).of(expected);
  }