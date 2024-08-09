public Classifiers getClassifiers(final String filterName) {

        Request request = Request.Get(CLASSIFIERS_PATH);

        if(filterName!=null && !filterName.isEmpty()) {
            Map<String, Object> queryParameters = new HashMap<String, Object>();
            queryParameters.put(FILTER_NAME,filterName);
            request.withQuery(queryParameters);
        }

        return executeRequest(request, Classifiers.class);
    }