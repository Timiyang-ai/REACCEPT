    @Test
    public void getKeyMap() {
        Map<String, AttributeValue> expectedResult = new HashMap<>();
        expectedResult.put("gsi_id", AttributeValue.builder().s("id123").build());
        expectedResult.put("gsi_sort", AttributeValue.builder().s("id456").build());
        assertThat(key.keyMap(FakeItemWithIndices.getTableSchema(), "gsi_1"), is(expectedResult));
    }