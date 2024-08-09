boolean resolveExpression(@Nonnull final FeedMetadata metadata, @Nonnull final NifiProperty property) {
        final ResolveResult variableResult = resolveVariables(property, metadata);
        final ResolveResult staticConfigResult = (!variableResult.isFinal) ? resolveStaticConfigProperty(property) : new ResolveResult(false, false);
        if (variableResult.isModified || staticConfigResult.isModified) {
            if (StringUtils.isEmpty(property.getValue())) {
                property.setValue(null);
            }
            return true;
        } else {
            return false;
        }
    }