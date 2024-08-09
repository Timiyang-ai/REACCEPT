    @Test
    public void getAdapter() throws Exception {
        List<TestItem> items = TestDataGenerator.genTestItemList(100);
        itemAdapter.set(items);

        assertThat(adapter.getAdapter(40)).isEqualTo(itemAdapter);
    }