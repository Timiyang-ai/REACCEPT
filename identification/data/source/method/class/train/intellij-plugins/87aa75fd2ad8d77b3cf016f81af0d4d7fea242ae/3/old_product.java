public static String getFullComponentPackage(String basePackage, String componentName) {
        if (isEmpty(componentName) || componentName.indexOf(TAPESTRY_PATH_SEPARATOR) == -1) {
            return basePackage != null ? basePackage : "";
        }

        String pathPath = componentName.substring(0, componentName.lastIndexOf(TAPESTRY_PATH_SEPARATOR));
        return new StringBuilder().append(basePackage).append(PACKAGE_SEPARATOR).append(pathPath.replace(TAPESTRY_PATH_SEPARATOR, PACKAGE_SEPARATOR)).toString();
    }