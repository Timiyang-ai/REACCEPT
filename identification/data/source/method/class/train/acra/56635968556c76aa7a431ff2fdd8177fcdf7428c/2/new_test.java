    @Test
    public void guessMimeType() {
        assertEquals(JSON_MIMETYPE, AcraContentProvider.guessMimeType(AcraContentProvider.getUriForFile(ApplicationProvider.getApplicationContext(), file)));
    }