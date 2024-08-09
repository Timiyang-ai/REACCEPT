    @Test
    public void setFilter() {
        dataProvider.setFilter(item -> item.getValue().equals("Foo"));

        assertEquals(36, sizeWithUnfilteredQuery());

        dataProvider.setFilter(item -> !item.getValue().equals("Foo"));

        assertEquals("Previous filter should be reset when setting a new one",
                64, sizeWithUnfilteredQuery());

        dataProvider.setFilter(null);

        assertEquals("Setting filter to null should remove all filters", 100,
                sizeWithUnfilteredQuery());
    }