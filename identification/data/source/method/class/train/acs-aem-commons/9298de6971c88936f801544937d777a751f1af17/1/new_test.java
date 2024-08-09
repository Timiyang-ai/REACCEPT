    @Test
    public void parseAssetFolderDefinitions() throws Exception {
        assetFolderCreator.parseAssetFolderDefinitions(actionManager);
        final int expected = 9 // Col 3
                + 2 // Col 2
                + 2 // Col 1
                + 3; // Numeric folders 2019/9/16

        assertEquals(expected, assetFolderCreator.assetFolderDefinitions.size());
    }