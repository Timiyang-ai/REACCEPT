  @Test
  public void getStemsTest() {
    RuleBasedAnalyzer analyzer = getAnalyzer("kitap");
    SingleAnalysis analysis = analyzer.analyze("kitap").get(0);
    Assert.assertEquals(toList("kitap"), analysis.getStems());

    analysis = analyzer.analyze("kitaplı").get(0);
    Assert.assertEquals(toList("kitap", "kitaplı"), analysis.getStems());

    analysis = analyzer.analyze("kitaplarda").get(0);
    Assert.assertEquals(toList("kitap"), analysis.getStems());

    analysis = analyzer.analyze("kitabımmış").get(0);
    Assert.assertEquals(toList("kitab", "kitabım"), analysis.getStems());

    analysis = analyzer.analyze("kitapçığa").get(0);
    Assert.assertEquals(toList("kitap", "kitapçığ"), analysis.getStems());

    analyzer = getAnalyzer("okumak");
    analysis = analyzer.analyze("okut").get(0);
    Assert.assertEquals(toList("oku", "okut"), analysis.getStems());
    analysis = analyzer.analyze("okuttur").get(0);
    Assert.assertEquals(toList("oku", "okut", "okuttur"), analysis.getStems());
    analysis = analyzer.analyze("okutturuluyor").get(0);
    Assert.assertEquals(toList("oku", "okut", "okuttur", "okutturul"), analysis.getStems());
    analysis = analyzer.analyze("okutturamıyor").get(0);
    Assert.assertEquals(toList("oku", "okut", "okuttur"), analysis.getStems());
    analysis = analyzer.analyze("okutturabiliyor").get(0);
    Assert.assertEquals(toList("oku", "okut", "okuttur", "okutturabil"), analysis.getStems());
  }