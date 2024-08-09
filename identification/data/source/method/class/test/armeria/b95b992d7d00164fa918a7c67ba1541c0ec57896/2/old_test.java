    @Test
    void responseHeadersSanitizer() {
        assertThatThrownBy(() -> builder.responseHeadersSanitizer(null))
                .isInstanceOf(NullPointerException.class);
        assertThat(builder.responseHeadersSanitizer()).isEqualTo(Function.identity());

        builder.responseHeadersSanitizer(HEADER_SANITIZER);
        assertThat(builder.responseHeadersSanitizer()).isEqualTo(HEADER_SANITIZER);
    }