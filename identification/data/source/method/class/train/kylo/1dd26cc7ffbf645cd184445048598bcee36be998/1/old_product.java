boolean resolveExpression(@Nonnull final FeedMetadata metadata, @Nonnull final NifiProperty property) {
        final ResolveResult variableResult = resolveVariables(property, metadata);
        final ResolveResult staticConfigResult = (!variableResult.isFinal) ? resolveStaticConfigProperty(property) : new ResolveResult(false, false);
        return variableResult.isModified || staticConfigResult.isModified;
    }