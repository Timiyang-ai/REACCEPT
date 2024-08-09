    @Test
    public void isDefaultButton() {
        // given:
        button.setDefaultButton(true);

        // then:
        assertThat(button, ButtonMatchers.isDefaultButton());
    }