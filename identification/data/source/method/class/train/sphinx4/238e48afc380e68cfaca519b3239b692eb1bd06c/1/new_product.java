public static SimpleConfigurable getDefaultInstance(Class<? extends SimpleConfigurable> targetClass, Map<String, Object> props) throws InstantiationException, PropertyException {
        PropSheet ps = getPropSheetInstanceFromClass(targetClass, props);
        SimpleConfigurable configurable = ps.getOwner();
        configurable.newProperties(ps);
        return configurable;
    }