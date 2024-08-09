public static String getComponentFileName(String componentName) {
        if (isEmpty(componentName)) {
            return "";
        }

        if (!componentName.contains(TAPESTRY_PATH_SEPARATOR)) {
            return componentName;
        }

        return componentName.substring(componentName.lastIndexOf(TAPESTRY_PATH_SEPARATOR) + 1);
    }