public static String formatDisplayText(String displayText) {
        if (displayText == null) {
            return null;
        }
        // See #883 for "SPLC" logic
        if (MyTextUtils.isAllCaps(displayText) && displayText.contains(" ") && !displayText.contains("SPLC")) {
            return MyTextUtils.toTitleCase(displayText);
        } else {
            return displayText;
        }
    }