    @Test
    void requestTrailersSanitizer() {
        assertThatThrownBy(() -> builder.requestTrailersSanitizer(null))
                .isInstanceOf(NullPointerException.class);
        assertThat(builder.requestTrailersSanitizer()).isEqualTo(Function.identity());

        builder.requestTrailersSanitizer(HEADER_SANITIZER);
        assertThat(builder.requestTrailersSanitizer()).isEqualTo(HEADER_SANITIZER);
    }