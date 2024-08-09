public static String getFullComponentPackage(String basePackage, String componentName) {
        if (isEmpty(componentName) || !componentName.contains(TAPESTRY_PATH_SEPARATOR)) {
            return basePackage != null ? basePackage : "";
        }

        String pathPath = componentName.substring(0, componentName.lastIndexOf(TAPESTRY_PATH_SEPARATOR));
        return basePackage + PACKAGE_SEPARATOR + pathPath.replace(TAPESTRY_PATH_SEPARATOR, PACKAGE_SEPARATOR);
    }