    @Test
    public void getName_ReturnsNameAsLowerCase() {

        // given
        final Status status = PASSED;
        final String refName = "passed";
        
        // when
        String rawName = status.getRawName();

        // then
        assertThat(rawName).isEqualTo(refName);
    }