@Test
  public void testDilemmas() {
    final Dilemma dilemma = service.dilemmas(problem, false);
    assertNotNull(dilemma);
    assertNotNull(dilemma.getProblem());
    assertNotNull(dilemma.getResolution());
  }