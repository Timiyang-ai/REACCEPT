    @Test
    void responseContentSanitizer() {
        assertThatThrownBy(() -> builder.responseContentSanitizer(null))
                .isInstanceOf(NullPointerException.class);
        assertThat(builder.responseContentSanitizer()).isEqualTo(Function.identity());

        builder.responseContentSanitizer(CONTENT_SANITIZER);
        assertThat(builder.responseContentSanitizer()).isEqualTo(CONTENT_SANITIZER);
    }