public static String getValue(ResourceBundle resourceBundle,String key,Object...arguments){
        Validate.notNull(resourceBundle, "resourceBundle can't be null!");
        Validate.notBlank(key, "key can't be null/empty!");

        if (!resourceBundle.containsKey(key)){
            LOGGER.warn("resourceBundle:[{}] don't containsKey:[{}]", resourceBundle, key);
            return StringUtils.EMPTY;
        }

        String value = resourceBundle.getString(key);
        if (isNullOrEmpty(value)){
            LOGGER.trace("resourceBundle has key:[{}],but value is null/empty", key);
        }
        return isNullOrEmpty(value) ? StringUtils.EMPTY : MessageFormatUtil.format(value, arguments);// 支持 arguments 为null,原样返回
    }