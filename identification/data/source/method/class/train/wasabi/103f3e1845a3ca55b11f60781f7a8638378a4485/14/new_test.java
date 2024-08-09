    @Test
    public void getAssignmentsQueueLength() throws Exception {
        assertThat(resource.getAssignmentsQueueLength().getStatus(), is(HttpStatus.SC_OK));
    }