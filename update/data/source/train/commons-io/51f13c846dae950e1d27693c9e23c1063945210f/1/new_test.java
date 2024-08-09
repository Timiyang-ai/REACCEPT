@Test
    public void testFilterSet() throws Exception {
        final File fileA = TestUtils.newFile(getTestDirectory(), "A");
        final File fileB = TestUtils.newFile(getTestDirectory(), "B");
        final Set<File> fileList = new HashSet<>(Arrays.asList(fileA, fileB));

        final IOFileFilter filter = FileFilterUtils.nameFileFilter("A");

        final Set<File> filteredSet = FileFilterUtils.filterSet(filter, fileList);

        assertTrue(filteredSet.contains(fileA));
        assertFalse(filteredSet.contains(fileB));
    }