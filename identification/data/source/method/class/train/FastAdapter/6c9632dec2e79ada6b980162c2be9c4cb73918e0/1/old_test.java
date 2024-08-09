    @Test
    public void select() throws Exception {
        itemAdapter.set(TestDataGenerator.genTestItemList(100));

        SelectExtension<TestItem> selectExtension = adapter.getExtension(SelectExtension.class);

        assertThat(selectExtension).isEqualTo(this.selectExtension);

        assertThat(selectExtension.getSelectedItems().size()).isEqualTo(0);
        assertThat(selectExtension.getSelections().size()).isEqualTo(0);

        selectExtension.select(10);

        assertThat(selectExtension.getSelectedItems().size()).isEqualTo(1);
        assertThat(selectExtension.getSelectedItems().iterator().next().getIdentifier()).isEqualTo(10);
        assertThat(selectExtension.getSelections().size()).isEqualTo(1);
        assertThat(selectExtension.getSelections().iterator().next()).isEqualTo(10);
    }