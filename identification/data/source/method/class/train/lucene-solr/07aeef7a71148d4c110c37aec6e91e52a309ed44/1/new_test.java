@Test
  public void testResolveAnalysisRequest() throws Exception {
    ModifiableSolrParams params = new ModifiableSolrParams();
    params.add(AnalysisParams.FIELD_NAME, "text,nametext");
    params.add(AnalysisParams.FIELD_TYPE, "whitetok,keywordtok");
    params.add(AnalysisParams.FIELD_VALUE, "the quick red fox jumped over the lazy brown dogs");
    params.add(CommonParams.Q, "fox brown");

    SolrQueryRequest req = new LocalSolrQueryRequest(h.getCore(), params);
    FieldAnalysisRequest request = handler.resolveAnalysisRequest(req);
    List<String> fieldNames = request.getFieldNames();
    assertEquals("Expecting 2 field names", 2, fieldNames.size());
    assertEquals("text", fieldNames.get(0));
    assertEquals("nametext", fieldNames.get(1));
    List<String> fieldTypes = request.getFieldTypes();
    assertEquals("Expecting 2 field types", 2, fieldTypes.size());
    assertEquals("whitetok", fieldTypes.get(0));
    assertEquals("keywordtok", fieldTypes.get(1));
    assertEquals("the quick red fox jumped over the lazy brown dogs", request.getFieldValue());
    assertEquals("fox brown", request.getQuery());
    assertFalse(request.isShowMatch());
    req.close();

    // testing overide of query value using analysis.query param
    params.add(AnalysisParams.QUERY, "quick lazy");
    req=new LocalSolrQueryRequest(h.getCore(), params);
    request = handler.resolveAnalysisRequest(req);
    assertEquals("quick lazy", request.getQuery());
    req.close();

    // testing analysis.showmatch param
    params.add(AnalysisParams.SHOW_MATCH, "false");
    req=new LocalSolrQueryRequest(h.getCore(), params);
    request = handler.resolveAnalysisRequest(req);
    assertFalse(request.isShowMatch());
    req.close();

    params.set(AnalysisParams.SHOW_MATCH, "true");
    req=new LocalSolrQueryRequest(h.getCore(), params);
    request = handler.resolveAnalysisRequest(req);
    assertTrue(request.isShowMatch());
    req.close();

    // testing absence of query value
    params.remove(CommonParams.Q);
    params.remove(AnalysisParams.QUERY);
    req=new LocalSolrQueryRequest(h.getCore(), params);
    request = handler.resolveAnalysisRequest(req);
    assertNull(request.getQuery());
    req.close();

    // test absence of index-time value and presence of q
    params.remove(AnalysisParams.FIELD_VALUE);
    params.add(CommonParams.Q, "quick lazy");
    request = handler.resolveAnalysisRequest(req);
    assertEquals("quick lazy", request.getQuery());
    req.close();

    // test absence of index-time value and presence of query
    params.remove(CommonParams.Q);
    params.add(AnalysisParams.QUERY, "quick lazy");
    request = handler.resolveAnalysisRequest(req);
    assertEquals("quick lazy", request.getQuery());
    req.close();

    // must fail if all of q, analysis.query or analysis.value are absent
    params.remove(CommonParams.Q);
    params.remove(AnalysisParams.QUERY);
    params.remove(AnalysisParams.FIELD_VALUE);
    try {
      request = handler.resolveAnalysisRequest(req);
      fail("Analysis request must fail if all of q, analysis.query or analysis.value are absent");
    } catch (SolrException e) {
      if (e.code() != SolrException.ErrorCode.BAD_REQUEST.code)  {
        fail("Unexpected exception");
      }
    } catch (Exception e) {
      fail("Unexpected exception");
    }

    req.close();
  }