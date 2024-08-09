@Factory
    public static Matcher<Color> hasClosestNamedColor(Color namedColor) {
        Optional<String> namedColorOptional = ColorUtils.getNamedColor(Integer.parseInt(
                namedColor.toString().substring(2, 8), 16));
        if (!namedColorOptional.isPresent()) {
            throw new AssertionError("given color: \"#" + namedColor.toString().substring(2, 8) +
                    "\" is not a named color\n" +
                    "See: https://docs.oracle.com/javase/9/docs/api/javafx/scene/doc-files/cssref.html#typecolor");
        }
        String descriptionText = "has closest named color " + getColorText(namedColor);
        return typeSafeMatcher(Color.class, descriptionText, color -> getColorText(color) +
                " which has closest named color: \"" + ColorUtils.getClosestNamedColor(
                        Integer.parseInt(color.toString().substring(2, 8), 16)) + "\"",
            color -> namedColor.equals(ColorUtils.getClosestNamedColor(color)));
    }