public boolean containsUsedString(String text) {
        if (text == null) {
            return false;
        }
        final String textToTest = text.toLowerCase();

        for (Evidence e : this.list) {
            if (e.isUsed() && e.getValue().toLowerCase().contains(textToTest)) {
                return true;
            }
        }
        return false;
    }