    @Test
    public void performDelete() {
        final StorIOSQLite storIOSQLite = mock(StorIOSQLite.class);
        final StorIOSQLite.LowLevel lowLevel = mock(StorIOSQLite.LowLevel.class);

        final String testTable = "test_table";
        final Set<String> testTags = singleton("test_tag");
        final DeleteQuery deleteQuery = DeleteQuery.builder()
                .table(testTable)
                .affectsTags(testTags)
                .build();

        when(storIOSQLite.lowLevel())
                .thenReturn(lowLevel);

        when(lowLevel.delete(deleteQuery))
                .thenReturn(1);

        final TestItem testItem = new TestItem();

        final DefaultDeleteResolver<TestItem> defaultDeleteResolver = new DefaultDeleteResolver<TestItem>() {
            @NonNull
            @Override
            public DeleteQuery mapToDeleteQuery(@NonNull TestItem testItem) {
                return deleteQuery;
            }
        };

        final DeleteResult deleteResult = defaultDeleteResolver.performDelete(storIOSQLite, testItem);

        verify(lowLevel).delete(any(DeleteQuery.class));
        verify(lowLevel).delete(deleteQuery);

        assertThat(deleteResult.numberOfRowsDeleted()).isEqualTo(1);
        assertThat(deleteResult.affectedTables()).isEqualTo(singleton(testTable));
        assertThat(deleteResult.affectedTags()).isEqualTo(testTags);
    }