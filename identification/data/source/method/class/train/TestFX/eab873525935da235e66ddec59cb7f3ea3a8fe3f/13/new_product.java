@Factory
    @Unstable(reason = "introduced in 4.0.12-alpha")
    public static Matcher<Color> isColor(Color color, ColorMatcher colorMatcher) {
        String descriptionText = "has color " + getColorText(color);
        return typeSafeMatcher(Color.class, descriptionText, ColorMatchers::getColorText,
            col -> colorMatcher.matchColors(col, color));
    }