@Test
  public void testCountTruePositives() {
    Assert.assertEquals(0, FMeasure.countTruePositives(new Object[] {}, new Object[] {}));
    Assert.assertEquals(gold.length, FMeasure.countTruePositives(gold, gold));
    Assert.assertEquals(0, FMeasure.countTruePositives(gold, predictedCompletelyDistinct));
    Assert.assertEquals(2, FMeasure.countTruePositives(gold, predicted));
  }