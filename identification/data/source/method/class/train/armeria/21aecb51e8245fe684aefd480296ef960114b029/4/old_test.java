    @Test
    void responseTrailersSanitizer() {
        assertThatThrownBy(() -> builder.responseTrailersSanitizer(null))
                .isInstanceOf(NullPointerException.class);
        assertThat(builder.responseTrailersSanitizer()).isEqualTo(Function.identity());

        builder.responseTrailersSanitizer(HEADER_SANITIZER);
        assertThat(builder.responseTrailersSanitizer()).isEqualTo(HEADER_SANITIZER);
    }