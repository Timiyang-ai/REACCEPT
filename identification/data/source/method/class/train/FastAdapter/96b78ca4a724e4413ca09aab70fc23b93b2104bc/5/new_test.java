    @Test
    public void getPosition() throws Exception {
        TestItem testItem = TestDataGenerator.genTestItem(1);
        itemAdapter.add(testItem);

        assertThat(adapter.getPosition(testItem)).isEqualTo(0);
    }