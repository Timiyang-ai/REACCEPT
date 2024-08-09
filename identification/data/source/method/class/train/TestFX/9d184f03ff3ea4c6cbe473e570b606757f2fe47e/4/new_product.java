public static Matcher<Window> isFocused() {
        return baseMatcher("Window is focused", Window::isFocused);
    }