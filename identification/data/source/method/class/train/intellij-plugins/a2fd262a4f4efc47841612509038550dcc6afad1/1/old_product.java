public static String getComponentFileName(String componentName) {
        if (isEmpty(componentName)) {
            return "";
        }

        if (componentName.indexOf(TAPESTRY_PATH_SEPARATOR) == -1) {
            return componentName;
        }

        return componentName.substring(componentName.lastIndexOf(TAPESTRY_PATH_SEPARATOR) + 1);
    }