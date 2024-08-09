@Test
  public void testCountTruePositives() {
    assertEquals(0, FMeasure.countTruePositives(new Object[]{}, new Object[]{}));
    assertEquals(gold.length, FMeasure.countTruePositives(gold, gold));
    assertEquals(0, FMeasure.countTruePositives(gold, predictedCompletelyDistinct));
    assertEquals(2, FMeasure.countTruePositives(gold, predicted));
  }