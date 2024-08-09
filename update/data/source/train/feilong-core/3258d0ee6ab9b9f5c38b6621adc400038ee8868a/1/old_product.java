public static <T> T readPropertiesToAliasBean(String baseName,Class<T> aliasBeanClass){
        Validate.notBlank(baseName, "baseName can't be null/empty!");
        Validate.notNull(aliasBeanClass, "aliasBeanClass can't be null!");
        return BeanUtil.populateAliasBean(newInstance(aliasBeanClass), readPropertiesToMap(baseName));
    }