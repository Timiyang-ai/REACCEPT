public static boolean checkMergePolicySupportsInMemoryFormat(String name, Object mergePolicy, InMemoryFormat inMemoryFormat,
                                                                 Version clusterVersion, boolean failFast, ILogger logger) {
        if (inMemoryFormat != NATIVE) {
            return true;
        }
        // RU_COMPAT_3_9 (in 3.11 just check instanceof SplitBrainMergePolicy)
        if (mergePolicy instanceof SplitBrainMergePolicy && clusterVersion.isGreaterOrEqual(V3_10)) {
            return true;
        }
        // RU_COMPAT_3_9 (in 3.11 just check failFast)
        if (failFast && clusterVersion.isGreaterOrEqual(V3_10)) {
            throw new InvalidConfigurationException(createSplitRecoveryWarningMsg(name, mergePolicy.getClass().getName()));
        }
        logger.warning(createSplitRecoveryWarningMsg(name, mergePolicy.getClass().getName()));
        return false;
    }