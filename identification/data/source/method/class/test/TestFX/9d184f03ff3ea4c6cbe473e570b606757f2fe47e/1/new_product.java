@Factory
    public static Matcher<Window> isFocused() {
        return baseMatcher("Window is focused", WindowMatchers::isFocused);
    }