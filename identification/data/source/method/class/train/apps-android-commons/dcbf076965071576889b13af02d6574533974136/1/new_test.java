    @Test
    public void extractCategoriesFromList() {
        List<String> strings = MediaDataExtractorUtil.extractCategoriesFromList("Watercraft 2018|Watercraft|2018");
        assertEquals(strings.size(), 3);
    }