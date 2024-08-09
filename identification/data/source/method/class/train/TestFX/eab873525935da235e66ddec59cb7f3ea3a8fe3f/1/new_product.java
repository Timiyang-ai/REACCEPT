public static Matcher<Color> hasClosestNamedColor(String namedColor) {
        Optional<Color> namedColorOptional = ColorUtils.getNamedColor(namedColor);
        if (!namedColorOptional.isPresent()) {
            throw new AssertionError("given color name: \"" + namedColor + "\" is not a named color\n" +
                    "See: https://docs.oracle.com/javase/9/docs/api/javafx/scene/doc-files/cssref.html#typecolor");
        }
        String descriptionText = "has closest named color " + getColorText(namedColorOptional.get());
        return typeSafeMatcher(Color.class, descriptionText, color -> getColorText(color) +
                        " which has closest named color: \"" + ColorUtils.getClosestNamedColor(
                Integer.parseInt(color.toString().substring(2, 8), 16)) + "\"",
            color -> namedColorOptional.get().equals(ColorUtils.getClosestNamedColor(color)));
    }