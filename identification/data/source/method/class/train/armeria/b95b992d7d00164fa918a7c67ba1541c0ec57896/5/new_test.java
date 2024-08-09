    @Test
    void requestContentSanitizer() {
        assertThatThrownBy(() -> builder.requestContentSanitizer(null))
                .isInstanceOf(NullPointerException.class);
        assertThat(builder.requestContentSanitizer()).isEqualTo(Function.identity());

        builder.requestContentSanitizer(CONTENT_SANITIZER);
        assertThat(builder.requestContentSanitizer()).isEqualTo(CONTENT_SANITIZER);
    }