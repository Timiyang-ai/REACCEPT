@Test
  public void isRing() {
    runQuery("geo:isRing(<gml:LinearRing><gml:coordinates>2,3 20,1 20,20 2,3" +
            "</gml:coordinates></gml:LinearRing>)", "true");

    runQuery("geo:isRing(<gml:LineString><gml:coordinates>11,10 20,1 20,20 12,13" +
            "</gml:coordinates></gml:LineString>)", "false");

    runError("geo:isRing(<gml:MultiLineString><gml:LineString><gml:coordinates>" +
            "1,1 0,0 2,1</gml:coordinates></gml:LineString><gml:LineString>" +
            "<gml:coordinates>2,1 3,3 4,4</gml:coordinates></gml:LineString>" +
            "</gml:MultiLineString>)", GeoErrors.qname(3));
    runError("geo:isClosed(" +
            "<gml:Point><gml:coordinates>2,3</gml:coordinates></gml:Point>)",
            GeoErrors.qname(3));
    runError("geo:isRing(" +
            "<gml:LineString><gml:coordinates>1,1</gml:coordinates></gml:LineString>)",
            GeoErrors.qname(2));
    runError("geo:isRing()", XPARGS.qname());
    runError("geo:isRing(text {'gml:Point'})", FUNCMP.qname());
    runError("geo:isRing(<Point><gml:coordinates>2,1</gml:coordinates></Point>)",
            GeoErrors.qname(1));
  }