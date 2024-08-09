public static SimpleConfigurable getDefaultInstance(Class<? extends SimpleConfigurable> targetClass, Map<String, Object> defaultProps) {
        return getPropSheetInstanceFromClass(targetClass, defaultProps).getOwner();
    }