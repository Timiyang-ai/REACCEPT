@Test
    public void testIsisDeactivate() throws Exception {
        controller.isisDeactivate();
        assertThat(controller, is(notNullValue()));
    }