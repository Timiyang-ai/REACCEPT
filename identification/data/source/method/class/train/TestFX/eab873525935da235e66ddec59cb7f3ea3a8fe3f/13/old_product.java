@Factory
    @Unstable(reason = "is missing apidocs")
    public static Matcher<Color> isColor(Color color,
                                         ColorMatcher colorMatcher) {
        String descriptionText = "has color (" + color.toString() + ")";
        return typeSafeMatcher(Color.class, descriptionText,
                actualColor -> isColor(actualColor, color, colorMatcher));
    }