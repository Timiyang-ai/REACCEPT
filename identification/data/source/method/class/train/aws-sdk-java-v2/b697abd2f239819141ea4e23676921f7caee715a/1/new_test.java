    @Test
    public void beforeWrite_noExtensions() {
        ChainMapperExtension extension = ChainMapperExtension.of();

        WriteModification result = extension.beforeWrite(fakeItems.get(0),
                                                         PRIMARY_CONTEXT,
                                                         FakeItem.getTableMetadata());

        assertThat(result.transformedItem(), is(nullValue()));
        assertThat(result.additionalConditionalExpression(), is(nullValue()));
    }