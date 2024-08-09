@Test
  public void testGetModel() {
    SpeechModel model = service.getModel(EN_BROADBAND16K);
    assertNotNull(model);
    assertNotNull(model.getName());
    assertNotNull(model.getRate());
  }