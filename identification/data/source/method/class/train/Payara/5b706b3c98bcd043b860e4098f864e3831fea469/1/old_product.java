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

        /*
         * If we still don't have a value, and it's a password parameter,
         * try using the simple name of the parameter (instead of the
         * "AS_ADMIN_" name).  This makes it easier to pass password
         * parameters when using the local CommandRunner API, e.g., for
         * embedded use.
         */
        if (paramValueStr == null && param.password())
            paramValueStr = getParameterValue(parameters, param.name(), true);

        // if paramValueStr is still null, then check to
        // see if the defaultValue is defined
        if (paramValueStr == null) {
            final String defaultValue = param.defaultValue();
            paramValueStr = defaultValue.equals("") ? null : defaultValue;
        }
        return paramValueStr;
    }