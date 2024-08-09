    @Test
    public void getItemCount() throws Exception {
        List<TestItem> items = TestDataGenerator.genTestItemList(100);
        itemAdapter.set(items);

        assertThat(adapter.getItemCount()).isEqualTo(100);
    }