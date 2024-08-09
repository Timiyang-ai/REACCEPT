@Test
  @Ignore
  public void testGetDateFormatEvaluator() {
    Evaluator dateFormatEval = EvaluatorBag.getDateFormatEvaluator();
    assertEquals(new SimpleDateFormat("yyyy-MM-dd").format(new Date()),
            dateFormatEval.evaluate(resolver, "'NOW',yyyy-MM-dd HH:mm"));

    Map<String, Object> map = new HashMap<String, Object>();
    map.put("key", new Date());
    resolver.addNamespace("A", map);

    assertEquals(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()),
            dateFormatEval.evaluate(resolver, "A.key, yyyy-MM-dd HH:mm"));
  }