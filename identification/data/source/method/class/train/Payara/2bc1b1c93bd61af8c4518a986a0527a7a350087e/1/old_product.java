static Object convertStringToObject(AnnotatedElement target,
                                    Class type, String paramValStr) {
        Param param = target.getAnnotation(Param.class);
        Object paramValue = paramValStr;
        if (type.isAssignableFrom(String.class)) {
            paramValue = paramValStr;
        } else if (type.isAssignableFrom(Properties.class)) {
            paramValue =
                convertStringToProperties(paramValStr, param.separator());
        } else if (type.isAssignableFrom(List.class)) {
            paramValue = convertStringToList(paramValStr, param.separator());
        } else if (type.isAssignableFrom(Boolean.class) ||
                    type.isAssignableFrom(boolean.class)) {
            String paramName = CommandModel.getParamName(param, target);
            paramValue = convertStringToBoolean(paramName, paramValStr);
        } else if (type.isAssignableFrom(Integer.class) ||
                    type.isAssignableFrom(int.class)) {
            String paramName = CommandModel.getParamName(param, target);
            paramValue = convertStringToInteger(paramName, paramValStr);
        } else if (type.isAssignableFrom(String[].class)) {
            paramValue =
                convertStringToStringArray(paramValStr, param.separator());
        } else if (type.isAssignableFrom(File.class)) {
            return new File(paramValStr);
        }
        return paramValue;
    }