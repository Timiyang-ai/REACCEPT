@Factory
    @Unstable(reason = "introduced in 4.0.12-alpha")
    public static Matcher<Color> isColor(String namedColor) {
        if (!ColorUtils.getNamedColor(namedColor).isPresent()) {
            throw new AssertionError("given color name: \"" + namedColor + "\" is not a named color\n" +
                    "See: https://docs.oracle.com/javase/9/docs/api/javafx/scene/doc-files/cssref.html#typecolor");
        }
        String descriptionText = "is \"" + namedColor + "\"";
        return typeSafeMatcher(Color.class, descriptionText, ColorMatchers::getColorText,
            color -> ColorUtils.getNamedColor(namedColor).map(col -> col.equals(color)).orElse(false));
    }