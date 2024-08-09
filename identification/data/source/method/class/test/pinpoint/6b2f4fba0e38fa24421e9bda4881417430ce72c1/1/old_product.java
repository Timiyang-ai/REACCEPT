public ApiDescription parse(String apiDescriptionString) {
        final int methodStart = apiDescriptionString.lastIndexOf(METHOD_PARAM_START);
        final int methodEnd = apiDescriptionString.lastIndexOf(METHOD_PARAM_END);
        final int classIndex = apiDescriptionString.lastIndexOf(DOT, methodStart);

        String className = parseClassName(apiDescriptionString, classIndex);
        ApiDescription api = new ApiDescription();
        api.setClassName(className);

        String methodName = parseMethodName(apiDescriptionString, methodStart, classIndex);
        api.setMethodName(methodName);

        String parameterDescriptor = apiDescriptionString.substring(methodStart + 1, methodEnd);
        String[] parameterList = parseParameter(parameterDescriptor);
        String[] simpleParameterList = parseSimpleParameter(parameterList);
        api.setSimpleParameter(simpleParameterList);
        return api;
    }