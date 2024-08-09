    @Test
    public void withSelectable() throws Exception {
        selectExtension.setSelectable(false);
        assertThat(selectExtension.isSelectable()).isFalse();
        selectExtension.setSelectable(true);
        assertThat(selectExtension.isSelectable()).isTrue();
    }