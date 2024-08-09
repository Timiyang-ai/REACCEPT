static Object convertListToObject(AnnotatedElement target,
                                    Class type, List<String> paramValList) {
        Param param = target.getAnnotation(Param.class);
        // does this parameter type allow multiple values?
        if (!param.multiple()) {
            if (paramValList.size() == 1)
                return convertStringToObject(target, type, paramValList.get(0));
            throw new UnacceptableValueException(
                localStrings.getLocalString("TooManyValues",
                    "Invalid parameter: {0}.  This parameter may not have " +
                    "more than one value.",
                    CommandModel.getParamName(param, target)));
        }

        Object paramValue = paramValList;
        if (type.isAssignableFrom(List.class)) {
            // the default case, nothing to do
        } else if (type.isAssignableFrom(String[].class)) {
            paramValue = paramValList.toArray(new String[paramValList.size()]);
        } else if (type.isAssignableFrom(Properties.class)) {
            paramValue = convertListToProperties(paramValList);
        }
        // XXX - could handle arrays of other types
        return paramValue;
    }