    @Test
    @DisplayName("Should Return Type MongoCredentials")
    public void getMongoChallengeCredentials_ShouldReturnTypeMongoCredentials() throws FileNotFoundException
    {
        assertThat(MongoDatabase.getMongoChallengeCredentials(null, TEST_PATH),
                instanceOf(MongoCredential.class));
    }