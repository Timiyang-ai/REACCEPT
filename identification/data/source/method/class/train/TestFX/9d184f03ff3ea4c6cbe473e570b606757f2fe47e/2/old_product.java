@Factory
    public static Matcher<Window> isShowing() {
        return baseMatcher("Window is showing", window -> isShowing(window));
    }