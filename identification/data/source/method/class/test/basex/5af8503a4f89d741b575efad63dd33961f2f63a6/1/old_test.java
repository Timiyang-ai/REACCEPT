@Test
  public void pointN() {
    runQuery("geo:pointN(<gml:LinearRing><gml:coordinates>2,3 20,1 20,20 2,3" +
            "</gml:coordinates></gml:LinearRing>, 1)",
            "<gml:Point srsName=\"0\"><gml:coordinates>2.0,3.0</gml:coordinates>" +
            "</gml:Point>");

    runQuery("geo:pointN(<gml:LineString><gml:coordinates>11,10 20,1 20,20 12,13" +
            "</gml:coordinates></gml:LineString>, 4)",
            "<gml:Point srsName=\"0\"><gml:coordinates>12.0,13.0</gml:coordinates>" +
            "</gml:Point>");

    runError("geo:pointN(<gml:MultiLineString><gml:LineString><gml:coordinates>" +
            "1,1 0,0 2,1</gml:coordinates></gml:LineString><gml:LineString>" +
            "<gml:coordinates>2,1 3,3 4,4</gml:coordinates></gml:LineString>" +
            "</gml:MultiLineString>, 4)", GeoErrors.qname(4));

    runError("geo:pointN(" +
            "<gml:unknown><gml:coordinates>1,1</gml:coordinates></gml:unknown>,1)",
            GeoErrors.qname(1));
    runError("geo:pointN(<gml:LineString><gml:coordinates>11,10 20,1 20,20 12,13" +
            "</gml:coordinates></gml:LineString>, 5)", GeoErrors.qname(6));
    runError("geo:pointN(<gml:LineString><gml:coordinates>11,10 20,1 20,20 12,13" +
            "</gml:coordinates></gml:LineString>, 0)", GeoErrors.qname(6));
    runError("geo:pointN(text {'a'},3)", FUNCMP.qname());
  }