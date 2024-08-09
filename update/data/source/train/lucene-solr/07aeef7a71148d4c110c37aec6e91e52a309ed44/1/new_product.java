FieldAnalysisRequest resolveAnalysisRequest(SolrQueryRequest req) {
    SolrParams solrParams = req.getParams();
    FieldAnalysisRequest analysisRequest = new FieldAnalysisRequest();

    boolean useDefaultSearchField = true;
    if (solrParams.get(AnalysisParams.FIELD_TYPE) != null) {
      analysisRequest.setFieldTypes(Arrays.asList(solrParams.get(AnalysisParams.FIELD_TYPE).split(",")));
      useDefaultSearchField = false;
    }
    if (solrParams.get(AnalysisParams.FIELD_NAME) != null) {
      analysisRequest.setFieldNames(Arrays.asList(solrParams.get(AnalysisParams.FIELD_NAME).split(",")));
      useDefaultSearchField = false;
    }
    if (useDefaultSearchField)  {
      analysisRequest.addFieldName(req.getSchema().getDefaultSearchFieldName());
    }
    analysisRequest.setQuery(solrParams.get(AnalysisParams.QUERY, solrParams.get(CommonParams.Q)));

    String value = solrParams.get(AnalysisParams.FIELD_VALUE);
    if (analysisRequest.getQuery() == null && value == null)  {
      throw new SolrException(SolrException.ErrorCode.BAD_REQUEST,
          "One of analysis.value or q or analysis.query parameters must be specified");
    }

    Iterable<ContentStream> streams = req.getContentStreams();
    if (streams != null) {
      // NOTE: Only the first content stream is currently processed
      for (ContentStream stream : streams) {
        Reader reader = null;
        try {
          reader = stream.getReader();
          value = IOUtils.toString(reader);
        } catch (IOException e) {
          // do nothing, leave value set to the request parameter
        }
        finally {
          IOUtils.closeQuietly(reader);
        }
        break;
      }
    }

    analysisRequest.setFieldValue(value);
    analysisRequest.setShowMatch(solrParams.getBool(AnalysisParams.SHOW_MATCH, false));
    return analysisRequest;
  }