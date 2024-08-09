@Factory
    public static Matcher<Window> isNotFocused() {
        return baseMatcher("Window is not focused", window -> !isFocused(window));
    }