@Test
  public void startPoint() {
    runQuery("geo:startPoint(<gml:LinearRing><gml:coordinates>1,1 20,1 20,20 1,1" +
            "</gml:coordinates></gml:LinearRing>)",
            "<gml:Point srsName=\"0\"><gml:coordinates>1.0,1.0</gml:coordinates>" +
            "</gml:Point>");

    runQuery("geo:startPoint(<gml:LineString><gml:coordinates>1,1 20,1 20,20 1,1" +
            "</gml:coordinates></gml:LineString>)",
            "<gml:Point srsName=\"0\"><gml:coordinates>1.0,1.0</gml:coordinates>" +
            "</gml:Point>");

    runError("geo:startPoint(<gml:MultiLineString><gml:LineString><gml:coordinates>" +
            "1,1 0,0 2,1</gml:coordinates></gml:LineString><gml:LineString>" +
            "<gml:coordinates>2,1 3,3 4,4</gml:coordinates></gml:LineString>" +
            "</gml:MultiLineString>)", GeoErrors.qname(3));

    runError("geo:startPoint(" +
            "<gml:LineString><gml:coordinates>1,1</gml:coordinates></gml:LineString>)",
            GeoErrors.qname(2));
    runError("geo:startPoint()", XPARGS.qname());
    runError("geo:startPoint(text {'gml:Point'})", FUNCMP.qname());
    runError("geo:startPoint(<gml:geo><gml:coordinates>2,1</gml:coordinates></gml:geo>)",
            GeoErrors.qname(1));
  }