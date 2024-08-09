    @Test
    @DisplayName("Should return a MongoClient instance")
    public void getMongoDbConnection_ShouldReturnTypeMongoClient()
    {
        MongoCredential credential =
                MongoCredential.createScramSha1Credential("test", "test", "test".toCharArray());
        assertThat(MongoDatabase.getMongoDbConnection(null), instanceOf(MongoClient.class));
        assertThat(MongoDatabase.getMongoDbConnection(null, credential), instanceOf(MongoClient.class));
    }