@Test
  public void isClosed() {
    runQuery("geo:isClosed(<gml:LinearRing><gml:coordinates>2,3 20,1 20,20 2,3" +
            "</gml:coordinates></gml:LinearRing>)", "true");
    runQuery("geo:isClosed(<gml:MultiLineString><gml:LineString><gml:coordinates>" +
            "1,1 0,0 2,1</gml:coordinates></gml:LineString><gml:LineString>" +
            "<gml:coordinates>2,1 3,3 4,4</gml:coordinates></gml:LineString>" +
            "</gml:MultiLineString>)", "false");

    runQuery("geo:isClosed(<gml:LineString><gml:coordinates>11,10 20,1 20,20 12,13" +
            "</gml:coordinates></gml:LineString>)", "false");
    runError("geo:isClosed(<gml:Polygon><gml:outerBoundaryIs><gml:LinearRing>" +
            "<gml:coordinates>1,1 2,1 5,3 1,1</gml:coordinates></gml:LinearRing>" +
            "</gml:outerBoundaryIs></gml:Polygon>)", GeoErrors.qname(3));

    runError("geo:isClosed(" +
            "<gml:LineString><gml:coordinates>1,1</gml:coordinates></gml:LineString>)",
            GeoErrors.qname(2));
    runError("geo:isClosed()", XPARGS.qname());
    runError("geo:isClosed(text {'gml:Point'})", FUNCMP.qname());
    runError("geo:isClosed(" +
            "<gml:Point><gml:coordinates>2,1</gml:coordinates></gml:Point>)",
            GeoErrors.qname(3));
  }