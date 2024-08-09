boolean cpeMatches(PropertyType cpeEntry, Identifier identifier) {
        if (cpeEntry.matches(identifier.getValue())) {
            return true;
        } else if (cpeHasNoVersion(cpeEntry)) {
            if (cpeEntry.isCaseSensitive()) {
                if (identifier.getValue().startsWith(cpeEntry.getValue())) {
                    return true;
                }
            } else {
                final String id = identifier.getValue().toLowerCase();
                final String check = cpeEntry.getValue().toLowerCase();
                if (id.startsWith(check)) {
                    return true;
                }
            }
        }
        return false;
    }