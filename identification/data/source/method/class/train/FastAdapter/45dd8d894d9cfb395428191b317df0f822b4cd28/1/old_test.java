    @Test
    public void getPreItemCount() throws Exception {
        List<TestItem> items = TestDataGenerator.genTestItemList(100);
        itemAdapter.set(items);

        assertThat(adapter.getPreItemCount(40)).isEqualTo(0);
    }