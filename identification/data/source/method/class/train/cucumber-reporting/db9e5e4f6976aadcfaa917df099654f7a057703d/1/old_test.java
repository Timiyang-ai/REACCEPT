    @Test
    public void getLabel_ReturnsNameStartingFromUpperCase() {

        // given
        final Status status = UNDEFINED;
        final String refLabel = "Undefined";

        // when
        String label = status.getLabel();

        // then
        assertThat(label).isEqualTo(refLabel);
    }