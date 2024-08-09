    @Test
    public void newInstance_oneTableWithoutTag() {
        final Changes changes = Changes.newInstance("test_table");
        assertThat(changes.affectedTables()).containsExactly("test_table");
        assertThat(changes.affectedTags()).isEmpty();
    }