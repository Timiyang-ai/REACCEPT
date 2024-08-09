    @Test
    public void setColumns_reorder() {
        // Will remove other columns
        grid.setColumns("length", "foo");

        List<Column<String, ?>> columns = grid.getColumns();

        assertEquals(2, columns.size());
        assertEquals("length", columns.get(0).getId());
        assertEquals("foo", columns.get(1).getId());
    }