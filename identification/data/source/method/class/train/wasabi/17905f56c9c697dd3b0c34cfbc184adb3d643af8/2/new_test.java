    @Test
    public void getMetadataCacheDetailsTest() throws Exception {
        assertThat(resource.getMetadataCacheDetails().getStatus(), is(HttpStatus.SC_OK));
    }