public static Matcher<Color> isColor(Color color, ColorMatcher colorMatcher) {
        String descriptionText = "has color " + getColorText(color);
        return typeSafeMatcher(Color.class, descriptionText, ColorMatchers::getColorText,
            col -> colorMatcher.matchColors(col, color));
    }