  @Test
  public void getLemmasTest() {
    RuleBasedAnalyzer analyzer = getAnalyzer("kitap");
    SingleAnalysis analysis = analyzer.analyze("kitap").get(0);
    Assert.assertEquals(toList("kitap"), analysis.getLemmas());

    analysis = analyzer.analyze("kitaplı").get(0);
    Assert.assertEquals(toList("kitap", "kitaplı"), analysis.getLemmas());

    analysis = analyzer.analyze("kitaplarda").get(0);
    Assert.assertEquals(toList("kitap"), analysis.getLemmas());

    analysis = analyzer.analyze("kitabımmış").get(0);
    Assert.assertEquals(toList("kitap", "kitabım"), analysis.getLemmas());

    analysis = analyzer.analyze("kitapçığa").get(0);
    Assert.assertEquals(toList("kitap", "kitapçık"), analysis.getLemmas());

    analyzer = getAnalyzer("okumak");
    analysis = analyzer.analyze("okut").get(0);
    Assert.assertEquals(toList("oku", "okut"), analysis.getLemmas());
    analysis = analyzer.analyze("okuttur").get(0);
    Assert.assertEquals(toList("oku", "okut", "okuttur"), analysis.getLemmas());
    analysis = analyzer.analyze("okutturuluyor").get(0);
    Assert.assertEquals(toList("oku", "okut", "okuttur", "okutturul"), analysis.getLemmas());
    analysis = analyzer.analyze("okutturamıyor").get(0);
    Assert.assertEquals(toList("oku", "okut", "okuttur"), analysis.getLemmas());
    analysis = analyzer.analyze("okutturabiliyor").get(0);
    Assert.assertEquals(toList("oku", "okut", "okuttur", "okutturabil"), analysis.getLemmas());

  }