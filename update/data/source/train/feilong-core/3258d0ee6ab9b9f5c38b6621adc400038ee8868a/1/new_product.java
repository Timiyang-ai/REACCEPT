public static <T> T readToAliasBean(String baseName,Class<T> aliasBeanClass){
        Validate.notBlank(baseName, "baseName can't be null/empty!");
        Validate.notNull(aliasBeanClass, "aliasBeanClass can't be null!");
        return BeanUtil.populateAliasBean(newInstance(aliasBeanClass), readToMap(baseName));
    }