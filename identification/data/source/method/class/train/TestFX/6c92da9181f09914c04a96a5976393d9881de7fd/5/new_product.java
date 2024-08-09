public static Matcher<Object> hasDimension(double width, double height) {
        String descriptionText = "has dimension (" + width + ", " + height + ")";
        return typeSafeMatcher(Dimension2D.class, descriptionText,
            dimension -> hasDimension(dimension, width, height));
    }