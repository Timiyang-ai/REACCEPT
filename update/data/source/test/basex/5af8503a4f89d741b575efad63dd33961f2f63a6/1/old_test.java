@Test
  public void x() {
    runQuery("geo:x(<gml:Point><gml:coordinates>2,1</gml:coordinates></gml:Point>)", "2");

    runError("geo:x(<gml:MultiPoint><gml:Point><gml:coordinates>1,1" +
            "</gml:coordinates></gml:Point><gml:Point><gml:coordinates>1,2" +
            "</gml:coordinates></gml:Point></gml:MultiPoint>)", GeoErrors.qname(5));

    runError("geo:x(" +
            "<gml:LinearRing><gml:coordinates>0,0 20,0 0,20 0,0</gml:coordinates>" +
            "</gml:LinearRing>)", GeoErrors.qname(5));

    runError("geo:x(<gml:Point><gml:coordinates></gml:coordinates></gml:Point>)",
        GeoErrors.qname(2));
    runError("geo:x(<gml:geo><gml:coordinates>2,1</gml:coordinates></gml:geo>)",
        GeoErrors.qname(1));
    runError("geo:x(text {'a'})", FUNCMP.qname());
  }