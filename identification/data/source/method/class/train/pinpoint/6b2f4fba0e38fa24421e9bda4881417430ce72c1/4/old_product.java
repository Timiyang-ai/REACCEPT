public ApiDescription parse(String apiDescriptionString) {
        if (apiDescriptionString == null) {
            throw new NullPointerException("apiDescriptionString must not be null");
        }

        final int methodStart = apiDescriptionString.lastIndexOf(METHOD_PARAM_START);
        if (methodStart == -1) {
            throw new IllegalArgumentException("'(' not found. invalid apiDescriptionString:" + apiDescriptionString);
        }

        final int methodEnd = apiDescriptionString.lastIndexOf(METHOD_PARAM_END);
        if (methodEnd == -1) {
            throw new IllegalArgumentException("')' not found. invalid apiDescriptionString:" + apiDescriptionString);
        }

        final int classIndex = apiDescriptionString.lastIndexOf(DOT, methodStart);
        if (classIndex == -1) {
            throw new IllegalArgumentException("'.' not found. invalid apiDescriptionString:" + apiDescriptionString);
        }

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