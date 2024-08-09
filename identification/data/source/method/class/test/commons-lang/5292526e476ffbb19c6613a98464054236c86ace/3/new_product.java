protected String resolveVariable(final String variableName, final StrBuilder buf, final int startPos, final int endPos) {
        final StrLookup<?> resolver = getVariableResolver();
        if (resolver == null) {
            return null;
        }
        return resolver.lookup(variableName);
    }