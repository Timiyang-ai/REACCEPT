@Test
  public void endPoint() {
    runQuery("geo:endPoint(<gml:LinearRing><gml:coordinates>2,3 20,1 20,20 2,3" +
            "</gml:coordinates></gml:LinearRing>)",
            "<gml:Point srsName=\"0\"><gml:coordinates>2.0,3.0</gml:coordinates>" +
            "</gml:Point>");

    runQuery("geo:endPoint(<gml:LineString><gml:coordinates>11,10 20,1 20,20 12,13" +
            "</gml:coordinates></gml:LineString>)",
            "<gml:Point srsName=\"0\"><gml:coordinates>12.0,13.0</gml:coordinates>" +
            "</gml:Point>");

    runError("geo:endPoint(<gml:MultiLineString><gml:LineString><gml:coordinates>" +
            "1,1 0,0 2,1</gml:coordinates></gml:LineString><gml:LineString>" +
            "<gml:coordinates>2,1 3,3 4,4</gml:coordinates></gml:LineString>" +
            "</gml:MultiLineString>)", GeoErrors.qname(4));
    runError("geo:endPoint(" +
            "<gml:LineString><gml:coordinates>1,1</gml:coordinates></gml:LineString>)",
            GeoErrors.qname(2));
    runError("geo:endPoint()", XPARGS.qname());
    runError("geo:endPoint(text {'gml:Point'})", FUNCMP.qname());
    runError("geo:endPoint(<gml:geo><gml:coordinates>2,1</gml:coordinates></gml:geo>)",
            GeoErrors.qname(1));
  }