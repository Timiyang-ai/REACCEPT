    @Test
    public void getPreItemCountByOrder() throws Exception {
        List<TestItem> items = TestDataGenerator.genTestItemList(100);
        itemAdapter.set(items);

        assertThat(adapter.getPreItemCountByOrder(itemAdapter.getOrder())).isEqualTo(0);
    }