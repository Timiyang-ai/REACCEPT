@Test
  public void relate() {
    run("geo:relate(" +
        "<gml:Point><gml:coordinates>18,11</gml:coordinates></gml:Point>, " +
        "<gml:Polygon><gml:outerBoundaryIs><gml:LinearRing><gml:coordinates>" +
        "10,10 20,10 30,40 20,40 10,10</gml:coordinates></gml:LinearRing>" +
        "</gml:outerBoundaryIs></gml:Polygon>, \"0********\")", "true");

    error("geo:relate(<gml:Point><gml:coordinates>18,11</gml:coordinates></gml:Point>,"
        + "<gml:LineString><gml:coordinates>11,10 20,1 20,20</gml:coordinates>" +
          "</gml:LineString>, \"0******\")", GeoErrors.qname(6));

    error("geo:relate(" +
        "<gml:Point><gml:coordinates>18,11</gml:coordinates></gml:Point>," +
        "<gml:LineString><gml:coordinates>11,10 20,1 20,20</gml:coordinates>" +
        "</gml:LineString>, \"0*******12*F\")", GeoErrors.qname(6));

    error("geo:relate()", JAVAARITY_X_X_X_X_X);
    error("geo:relate(" +
        "<gml:Line><gml:coordinates>1,1 55,99 2,1</gml:coordinates></gml:Line>," +
        "<gml:LineString></gml:LineString>, \"0********\")", GeoErrors.qname(1));
    error("geo:relate(" +
        "<gml:Point><gml:coordinates>1,1</gml:coordinates></gml:Point>," +
        " \"0********\")", JAVAARITY_X_X_X_X_X);
  }