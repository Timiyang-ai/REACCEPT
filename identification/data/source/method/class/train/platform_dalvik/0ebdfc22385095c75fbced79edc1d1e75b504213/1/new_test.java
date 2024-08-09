    @Test
    public void test_merge_dexesWithEmptyFieldsSection() throws IOException {
        List<Dex> outputDexes = new ArrayList<>();
        outputDexes.add(getDexForClass(NoFieldsClassA.class));
        outputDexes.add(getDexForClass(NoFieldsClassB.class));

        Dex merged =
                new DexMerger(
                        outputDexes.toArray(new Dex[outputDexes.size()]),
                        CollisionPolicy.FAIL,
                        new DxContext())
                        .merge();
        assertNotNull(merged);
        assertNotNull(merged.getTableOfContents());
        assertEquals(0, merged.getTableOfContents().fieldIds.off);
    }