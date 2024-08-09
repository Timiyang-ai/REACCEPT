@Test
  @Ignore
  public void testGetDateFormatEvaluator() {
    Evaluator dateFormatEval = EvaluatorBag.getDateFormatEvaluator();
    resolver.context = new ContextImpl(null, resolver, null, 0, Collections.EMPTY_MAP, null, null);

    assertEquals(new SimpleDateFormat("yyyy-MM-dd").format(new Date()),
            dateFormatEval.evaluate("'NOW','yyyy-MM-dd HH:mm'", resolver.context));

    Map<String, Object> map = new HashMap<String, Object>();
    map.put("key", new Date());
    resolver.addNamespace("A", map);

    assertEquals(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()),
            dateFormatEval.evaluate("A.key, 'yyyy-MM-dd HH:mm'", resolver.context));
  }