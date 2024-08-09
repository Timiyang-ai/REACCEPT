    @Test
    public void isCancelButton() {
        // given:
        button.setCancelButton(true);

        // then:
        assertThat(button, ButtonMatchers.isCancelButton());
    }