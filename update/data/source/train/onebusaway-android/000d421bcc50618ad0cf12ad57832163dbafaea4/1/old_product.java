public static String formatDisplayText(String displayText) {
        if (displayText == null) {
            return null;
        }
        if (MyTextUtils.isAllCaps(displayText) && displayText.contains(" ")) {
            return MyTextUtils.toTitleCase(displayText);
        } else {
            return displayText;
        }
    }