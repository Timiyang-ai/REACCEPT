    @Test
    @DisplayName("Should Return Type String")
    public void getMongoChallengeCollName_ShouldReturnTypeString()
    {
        assertThat(MongoDatabase.getMongoChallengeCollName(null, TEST_PATH),
                instanceOf(String.class));
    }