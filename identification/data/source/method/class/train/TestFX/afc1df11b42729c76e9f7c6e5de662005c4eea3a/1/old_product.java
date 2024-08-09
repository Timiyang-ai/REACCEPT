@Factory
    public static Matcher<Node> isDefaultButton() {
        return typeSafeMatcher(Button.class, "is default button", Button::isDefaultButton);
    }