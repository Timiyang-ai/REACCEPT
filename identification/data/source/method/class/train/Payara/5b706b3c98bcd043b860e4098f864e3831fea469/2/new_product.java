static String getParamValueString(final ParameterMap parameters,
                               final Param param,
                               final AnnotatedElement target,
                               final ExecutionContext context) {
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
        // if paramValueStr is still null, then check to see if a defaultCalculator
        // is defined, and if so, call the class to get the default value
        if (paramValueStr == null) {
            Class<? extends ParamDefaultCalculator> dc = param.defaultCalculator();
            if (dc != ParamDefaultCalculator.class) {
                try {
                    ParamDefaultCalculator pdc = dc.newInstance();
                    paramValueStr = pdc.defaultValue(context);
                } catch (InstantiationException ex) { // @todo Java SE 7 - use multi catch
                    Logger.getLogger(MapInjectionResolver.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(MapInjectionResolver.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return paramValueStr;
    }