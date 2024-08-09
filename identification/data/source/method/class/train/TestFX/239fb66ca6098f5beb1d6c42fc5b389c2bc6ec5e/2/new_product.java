public static Matcher<Button> isCancelButton() {
        return typeSafeMatcher(Button.class, "is cancel button", Button::isCancelButton);
    }