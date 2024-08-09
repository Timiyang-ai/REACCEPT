    @Test
    public void test_merge_empty_current_and_provided_with_partition_by() {
        var provided = new Window("w", partitionBy, List.of(), Optional.empty());

        var newWindowDef = emptyWindow.merge(provided);
        assertThat(newWindowDef.getPartitions(), is(provided.getPartitions()));
    }