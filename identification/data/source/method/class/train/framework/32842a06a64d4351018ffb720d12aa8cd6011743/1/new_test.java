    @Test
    public void setSortOrder() {
        Column<String, ?> column1 = grid.getColumns().get(1);
        Column<String, ?> column2 = grid.getColumns().get(2);
        List<GridSortOrder<String>> order = Arrays.asList(
                new GridSortOrder<>(column2, SortDirection.DESCENDING),
                new GridSortOrder<>(column1, SortDirection.ASCENDING));
        grid.setSortOrder(order);

        List<GridSortOrder<String>> sortOrder = grid.getSortOrder();
        assertEquals(column2, sortOrder.get(0).getSorted());
        assertEquals(SortDirection.DESCENDING, sortOrder.get(0).getDirection());

        assertEquals(column1, sortOrder.get(1).getSorted());
        assertEquals(SortDirection.ASCENDING, sortOrder.get(1).getDirection());
    }