@Factory
    public static Matcher<Node> isCancelButton() {
        return typeSafeMatcher(Button.class, "is cancel button", Button::isCancelButton);
    }