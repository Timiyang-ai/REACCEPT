@Test
  public void testGetModels() {
    List<SpeechModel> models = service.getModels();
    assertNotNull(models);
    assertTrue(!models.isEmpty());
  }