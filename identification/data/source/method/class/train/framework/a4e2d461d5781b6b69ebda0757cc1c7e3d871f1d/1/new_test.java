    @Test
    public void clearSortOrder() throws Exception {
        Column<String, ?> column = grid.getColumns().get(1);
        grid.sort(column);

        grid.clearSortOrder();

        assertEquals(0, grid.getSortOrder().size());

        // Make sure state is updated.
        assertEquals(0, state.sortColumns.length);
        assertEquals(0, state.sortDirs.length);
    }