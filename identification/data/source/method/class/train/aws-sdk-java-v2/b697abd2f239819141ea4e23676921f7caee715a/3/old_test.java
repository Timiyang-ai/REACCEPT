    @Test
    public void getPartitionKeyValue() {
        assertThat(key.partitionKeyValue(),
                   is(AttributeValue.builder().s("id123").build()));
    }