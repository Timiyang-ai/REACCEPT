static String getParamValueString(final ParameterMap parameters,
                               final Param param,
                               final AnnotatedElement target) {
        String paramValueStr = getParameterValue(parameters,
                                      CommandModel.getParamName(param, target),
                                      true);
        if (paramValueStr == null && param.alias().length() > 0) {
            // check for alias
            paramValueStr = getParameterValue(parameters, param.alias(), true);
        }
        if (paramValueStr == null) {
            // check for shortName
            paramValueStr = parameters.getOne(param.shortName());
        }
        
        // if paramValueStr is still null, then check to
        // see if the defaultValue is defined
        if (paramValueStr == null) {
            final String defaultValue = param.defaultValue();
            paramValueStr = defaultValue.equals("") ? null : defaultValue;
        }
        return paramValueStr;
    }