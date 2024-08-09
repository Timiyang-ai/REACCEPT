    @Test
    public void getRelativeInfo() throws Exception {
        List<TestItem> items = TestDataGenerator.genTestItemList(100);
        TestItem item = items.get(40);
        itemAdapter.set(items);

        assertThat(adapter.getRelativeInfo(40).getItem()).isEqualTo(item);
        assertThat(adapter.getRelativeInfo(40).getAdapter()).isEqualTo(itemAdapter);
        assertThat(adapter.getRelativeInfo(40).getPosition()).isEqualTo(40);

        assertThat(adapter.getRelativeInfo(0).getPosition()).isEqualTo(0);
        assertThat(adapter.getRelativeInfo(100).getItem()).isEqualTo(null);
    }