@Factory
    public static Matcher<Window> isNotShowing() {
        return baseMatcher("Window is not showing", window -> !isShowing(window));
    }