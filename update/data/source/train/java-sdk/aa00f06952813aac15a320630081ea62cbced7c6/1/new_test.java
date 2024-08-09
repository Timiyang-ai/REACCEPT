@Test
  public void testGetChatTone() {
    List<Utterance> utterances = new ArrayList<>();
    for (int i = 0; i < texts.length; i++) {
      Utterance utterance = new Utterance.Builder()
          .text(texts[i])
          .user(users[i])
          .build();
      utterances.add(utterance);
    }
    ToneChatRequest toneChatInput = new ToneChatRequest.Builder()
        .utterances(utterances)
        .build();


    UtterancesTone utterancesTone = service.getChatTone(toneChatInput).execute();

    Assert.assertNotNull(utterancesTone);
    Assert.assertNotNull(utterancesTone.getUtterancesTone());
    Assert.assertEquals(4, utterancesTone.getUtterancesTone().size());
    Assert.assertEquals("My charger isn't working.", utterancesTone.getUtterancesTone().get(0).getText());
  }