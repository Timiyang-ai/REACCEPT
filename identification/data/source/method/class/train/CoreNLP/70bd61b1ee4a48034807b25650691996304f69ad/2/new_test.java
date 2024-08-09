  @Test
  public void extractNER() {
    assertEquals("O", Util.guessNER(mockLabels("the black cat")));
    assertEquals(new Span(2, 3), Util.extractNER(mockLabels("the president Obama_PERSON"), new Span(2, 3)));
    assertEquals(new Span(2, 3), Util.extractNER(mockLabels("the president Obama_PERSON"), new Span(1, 3)));
    assertEquals(new Span(2, 3), Util.extractNER(mockLabels("the president Obama_PERSON"), new Span(0, 3)));
    assertEquals(new Span(2, 4), Util.extractNER(mockLabels("the President_TITLE Barack_PERSON Obama_PERSON"), new Span(2, 4)));
    assertEquals(new Span(2, 4), Util.extractNER(mockLabels("the President_TITLE Barack_PERSON Obama_PERSON"), new Span(2, 3)));
    assertEquals(new Span(1, 2), Util.extractNER(mockLabels("the President_TITLE Barack_PERSON Obama_PERSON"), new Span(0, 2)));
    assertEquals(new Span(1, 2), Util.extractNER(mockLabels("the President_TITLE Barack_PERSON Obama_PERSON visited China_LOCATION"), new Span(0, 2)));
    assertEquals(new Span(2, 4), Util.extractNER(mockLabels("the President_TITLE Barack_PERSON Obama_PERSON visited China_LOCATION"), new Span(2, 5)));
    assertEquals(new Span(2, 4), Util.extractNER(mockLabels("the President_TITLE Barack_PERSON Obama_PERSON visited China_LOCATION"), new Span(2, 6)));
    assertEquals(new Span(5, 6), Util.extractNER(mockLabels("the President_TITLE Barack_PERSON Obama_PERSON visited China_LOCATION"), new Span(5, 6)));
    assertEquals(new Span(5, 6), Util.extractNER(mockLabels("the President_TITLE Barack_PERSON Obama_PERSON visited China_LOCATION"), new Span(4, 6)));
  }