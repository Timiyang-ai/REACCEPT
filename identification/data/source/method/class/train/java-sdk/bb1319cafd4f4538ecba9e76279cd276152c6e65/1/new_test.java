@Test
  public void testGetModels() {
    List<SpeechModel> models = service.getModels().execute();
    assertNotNull(models);
    assertTrue(!models.isEmpty());
  }