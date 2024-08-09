@Factory
    public static Matcher<Window> isShowing() {
        return baseMatcher("Window is showing", Window::isShowing);
    }