    @Test
    public void getPrimaryKeyMap() {
        Map<String, AttributeValue> expectedResult = new HashMap<>();
        expectedResult.put("id", AttributeValue.builder().s("id123").build());
        expectedResult.put("sort", AttributeValue.builder().s("id456").build());
        assertThat(key.primaryKeyMap(FakeItemWithIndices.getTableSchema()), is(expectedResult));
    }