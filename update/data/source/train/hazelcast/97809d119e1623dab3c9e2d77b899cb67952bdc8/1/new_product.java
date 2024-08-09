public static boolean checkMergePolicySupportsInMemoryFormat(String name, Object mergePolicy, InMemoryFormat inMemoryFormat,
            boolean failFast, ILogger logger) {
        if (inMemoryFormat != NATIVE) {
            return true;
        }
        if (mergePolicy instanceof SplitBrainMergePolicy) {
            return true;
        }
        if (failFast) {
            throw new InvalidConfigurationException(createSplitRecoveryWarningMsg(name, mergePolicy.getClass().getName()));
        }
        logger.warning(createSplitRecoveryWarningMsg(name, mergePolicy.getClass().getName()));
        return false;
    }