public static Matcher<Color> isColor(Color color) {
        String descriptionText = "has color " + getColorText(color);
        return typeSafeMatcher(Color.class, descriptionText, ColorMatchers::getColorText,
            col -> col.equals(color));
    }