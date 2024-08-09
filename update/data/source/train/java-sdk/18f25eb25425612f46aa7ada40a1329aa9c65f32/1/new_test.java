@Test
  public void testGetChatTone() {
    UtterancesTone utterancesTone = service.getChatTone(toneChatInput).execute();

    Assert.assertNotNull(utterancesTone);
    Assert.assertNotNull(utterancesTone.getUtterancesTone());
    Assert.assertEquals(4, utterancesTone.getUtterancesTone().size());
    Assert.assertEquals("My charger isn't working.", utterancesTone.getUtterancesTone().get(0).getText());
  }