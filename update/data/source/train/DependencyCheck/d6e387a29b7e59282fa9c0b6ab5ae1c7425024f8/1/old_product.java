public boolean containsUsedString(String text) {
        if (text == null) {
            return false;
        }
        text = text.toLowerCase();

        for (Evidence e : this.list) {
            if (e.used && e.value.toLowerCase().contains(text)) {
                return true;
            }
        }
        return false;
    }