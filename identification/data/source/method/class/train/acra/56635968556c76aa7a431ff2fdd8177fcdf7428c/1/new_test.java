    @Test
    public void getAttachments() throws Exception {
        Uri uri = Uri.parse("content://not-a-valid-content-uri");
        List<Uri> result = new DefaultAttachmentProvider().getAttachments(ApplicationProvider.getApplicationContext(), new CoreConfigurationBuilder(new Application()).setAttachmentUris(uri.toString()).build());
        assertThat(result, hasSize(1));
        assertEquals(uri, result.get(0));
    }