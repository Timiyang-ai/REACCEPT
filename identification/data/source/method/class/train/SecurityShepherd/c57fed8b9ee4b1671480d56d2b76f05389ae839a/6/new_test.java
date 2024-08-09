    @Test
    @DisplayName("Must return type (Mongo) DB")
    public void getMongoDatabase_ShouldReturnTypeDB()
    {
        assertThat(MongoDatabase.getMongoDatabase(mongoClient), instanceOf(DB.class));
    }