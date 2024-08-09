@VisibleForTesting
    String tagsToString(final Set<String> tags) {
        final ArrayList<String> sortedTags = new ArrayList<>(tags == null ? Collections.emptySet() : tags);
        // Sort tags for the sake of determinism (e.g., tests)
        sortedTags.sort(Comparator.naturalOrder());
        final String joinedString = StringUtils.join(sortedTags, ',');
        // Escape quotes
        return StringUtils.replaceAll(StringUtils.replaceAll(joinedString, "\'", "\\\'"), "\"", "\\\"");
    }