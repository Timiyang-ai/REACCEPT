@Factory
    public static Matcher<Window> isFocused() {
        return baseMatcher("Window is focused", window -> isFocused(window));
    }