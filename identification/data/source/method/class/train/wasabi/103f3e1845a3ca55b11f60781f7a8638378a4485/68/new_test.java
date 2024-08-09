    @Test
    public void getEventsQueueLength() throws Exception {
        assertThat(resource.getEventsQueueLength().getStatus(), is(HttpStatus.SC_OK));
    }