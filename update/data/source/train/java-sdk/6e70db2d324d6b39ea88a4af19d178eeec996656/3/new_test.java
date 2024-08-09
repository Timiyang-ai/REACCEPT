@Test
  public void testDeleteClassifier() throws InterruptedException {
    DeleteClassifierOptions deleteOptions = new DeleteClassifierOptions.Builder()
        .classifierId(classifierId)
        .build();
    service.deleteClassifier(deleteOptions);
  }