boolean identifierMatches(String identifierType, PropertyType suppressionEntry, Identifier identifier) {
        if (identifierType.equals(identifier.getType())) {
            if (suppressionEntry.matches(identifier.getValue())) {
                return true;
            } else if (cpeHasNoVersion(suppressionEntry)) {
                if (suppressionEntry.isCaseSensitive()) {
                    if (identifier.getValue().startsWith(suppressionEntry.getValue())) {
                        return true;
                    }
                } else {
                    final String id = identifier.getValue().toLowerCase();
                    final String check = suppressionEntry.getValue().toLowerCase();
                    if (id.startsWith(check)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }