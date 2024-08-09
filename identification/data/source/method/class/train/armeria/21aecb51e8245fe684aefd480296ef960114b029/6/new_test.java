    @Test
    void responseCauseSanitizer() {
        assertThatThrownBy(() -> builder.responseCauseSanitizer(null)).isInstanceOf(NullPointerException.class);
        assertThat(builder.responseCauseSanitizer()).isEqualTo(Function.identity());

        builder.responseCauseSanitizer(CAUSE_SANITIZER);
        assertThat(builder.responseCauseSanitizer()).isEqualTo(CAUSE_SANITIZER);
    }