private static URL getResource(ClassLoader classLoader,String resourceName){
        Validate.notNull(classLoader, "classLoader can't be null!");
        Validate.notNull(resourceName, "resourceName can't be null!");

        boolean startsWithSlash = resourceName.startsWith("/");
        String usePath = startsWithSlash ? StringUtil.substring(resourceName, 1) : resourceName;
        URL result = classLoader.getResource(usePath);

        LOGGER.info("search resource:[\"{}\"] in [{}],result:[{}]", resourceName, classLoader, result);
        return result;
    }