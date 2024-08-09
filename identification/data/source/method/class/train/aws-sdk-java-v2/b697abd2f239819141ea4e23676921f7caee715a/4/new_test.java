    @Test
    public void afterRead_noExtensions() {
        ChainMapperExtension extension = ChainMapperExtension.of();

        ReadModification result = extension.afterRead(fakeItems.get(0), PRIMARY_CONTEXT, FakeItem.getTableMetadata());

        assertThat(result.transformedItem(), is(nullValue()));
    }