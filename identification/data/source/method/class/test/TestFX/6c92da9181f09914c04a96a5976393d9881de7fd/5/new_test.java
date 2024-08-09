    @Test
    public void hasSelectedItem() {
        // given:
        assertThat(comboBox, ComboBoxMatchers.hasSelectedItem("alice"));

        // when:
        clickOn(".combo-box-base");
        type(KeyCode.DOWN);
        type(KeyCode.ENTER);

        // then:
        assertThat(comboBox, ComboBoxMatchers.hasSelectedItem("bob"));
    }