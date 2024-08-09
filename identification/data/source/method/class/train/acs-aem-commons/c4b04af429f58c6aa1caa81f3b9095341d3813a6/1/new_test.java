    @Test
    public void createAssetFolders() throws Exception {
        assetFolderCreator.primary = AssetFolderCreator.AssetFolderBuilder.TITLE_AND_NODE_NAME;
        assetFolderCreator.fallback = AssetFolderCreator.AssetFolderBuilder.LOWERCASE_WITH_DASHES;

        assetFolderCreator.parseAssetFolderDefinitions(actionManager);
        assetFolderCreator.createAssetFolders(actionManager);

        assertTrue(context.resourceResolver().hasChanges());

        assertEquals("Michigan",
                context.resourceResolver().getResource("/content/dam/mi/jcr:content").getValueMap().get("jcr:title", String.class));

        assertEquals("Charlestown",
                context.resourceResolver().getResource("/content/dam/ma/boston/charlestown/jcr:content").getValueMap().get("jcr:title", String.class));

        assertEquals("West Michigan",
                context.resourceResolver().getResource("/content/dam/mi/west-mi/jcr:content").getValueMap().get("jcr:title", String.class));

        assertEquals("16",
                context.resourceResolver().getResource("/content/dam/2019/9/16/jcr:content").getValueMap().get("jcr:title", String.class));

    }