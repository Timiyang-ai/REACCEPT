private List<String> expand(List<String> strs) {
        return emptyToNull(nullToEmpty(strs).stream().map(this::expand).collect(toList()));
    }