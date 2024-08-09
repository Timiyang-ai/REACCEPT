@Test
    public void getBuildTest() {
        String method = getBuild(CLASS_NAME);
        assertThat(true, is(method.equals(FOUR_SPACE_INDENTATION + PUBLIC + SPACE + CLASS_NAME + SPACE + BUILD
                + OPEN_PARENTHESIS + CLOSE_PARENTHESIS + SPACE + OPEN_CURLY_BRACKET + NEW_LINE + EIGHT_SPACE_INDENTATION
                + RETURN + SPACE + NEW + SPACE + "Default" + CLASS_NAME + OPEN_PARENTHESIS + THIS + CLOSE_PARENTHESIS
                + SEMI_COLAN + NEW_LINE + FOUR_SPACE_INDENTATION + CLOSE_CURLY_BRACKET)));

    }