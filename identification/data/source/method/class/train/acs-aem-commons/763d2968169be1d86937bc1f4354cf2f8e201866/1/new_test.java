    @Test
    public void accepts_slingFolder() throws Exception {
        request.setResource(slingFolderResource);

        assertTrue(assetsFolderPropertiesSupport.accepts(request));
    }