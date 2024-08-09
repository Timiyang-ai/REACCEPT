@Test
    public void analyseSentimentForFaultyText() {
        final TextSentiment expectedDocumentSentiment = new TextSentiment(TextSentimentClass.NEUTRAL, 0.02, 0.91, 0.07, 5, 0);
        final List<TextSentiment> expectedSentenceSentiments = Arrays.asList(
            new TextSentiment(TextSentimentClass.NEUTRAL, 0.02, 0.91, 0.07, 1, 0),
            new TextSentiment(TextSentimentClass.NEUTRAL, 0.02, 0.91, 0.07, 4, 1));

        StepVerifier
            .create(client.analyzeSentiment("!@#%%"))
            .assertNext(response -> {
                validateAnalysedSentiment(expectedDocumentSentiment, response.getDocumentSentiment());
                validateAnalysedSentenceSentiment(expectedSentenceSentiments, response.getSentenceSentiments());
            }).verifyComplete();
    }