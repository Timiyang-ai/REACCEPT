    @Test
    public void getItem() throws Exception {
        List<TestItem> items = TestDataGenerator.genTestItemList(100);
        TestItem item = items.get(40);
        itemAdapter.set(items);

        assertThat(adapter.getItem(40)).isEqualTo(item);
    }