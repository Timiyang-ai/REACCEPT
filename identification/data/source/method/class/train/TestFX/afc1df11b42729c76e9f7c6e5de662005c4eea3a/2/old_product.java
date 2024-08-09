@Factory
    public static Matcher<Button> isDefaultButton() {
        return typeSafeMatcher(Button.class, "is default button", Button::isDefaultButton);
    }