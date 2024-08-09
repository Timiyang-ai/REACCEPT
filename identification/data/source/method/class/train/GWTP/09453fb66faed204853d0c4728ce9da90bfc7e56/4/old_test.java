    @Test
    public void valueOf_wildcard() {
        // when
        ContentType contentType = ContentType.valueOf("*");

        // then
        assertThat(contentType.getType()).isEqualTo("*");
        assertThat(contentType.getSubType()).isEqualTo("*");
        assertThat(contentType.getParameters()).isEmpty();
    }