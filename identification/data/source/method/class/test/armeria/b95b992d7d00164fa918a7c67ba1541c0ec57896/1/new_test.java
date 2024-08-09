    @Test
    void requestHeadersSanitizer() {
        assertThatThrownBy(() -> builder.requestHeadersSanitizer(null))
                .isInstanceOf(NullPointerException.class);
        assertThat(builder.requestHeadersSanitizer()).isEqualTo(Function.identity());

        builder.requestHeadersSanitizer(HEADER_SANITIZER);
        assertThat(builder.requestHeadersSanitizer()).isEqualTo(HEADER_SANITIZER);
    }