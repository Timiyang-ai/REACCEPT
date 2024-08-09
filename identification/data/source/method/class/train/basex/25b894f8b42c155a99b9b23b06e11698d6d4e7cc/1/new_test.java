@Test
  public void buffer() {
    run("geo:buffer(<gml:Polygon><gml:outerBoundaryIs><gml:LinearRing>" +
        "<gml:coordinates>10,10 20,10 30,40 20,40 10,10</gml:coordinates>" +
        "</gml:LinearRing></gml:outerBoundaryIs></gml:Polygon>,xs:double(0))",
        "<gml:Polygon xmlns:gml=\"http://www.opengis.net/gml\">" +
        "<gml:outerBoundaryIs><gml:LinearRing><gml:coordinates>" +
        "10.0,10.0 20.0,40.0 30.0,40.0 20.0,10.0 10.0,10.0</gml:coordinates>" +
        "</gml:LinearRing></gml:outerBoundaryIs></gml:Polygon>");

    run("geo:buffer(<gml:LineString><gml:coordinates>1,1 5,9 2,1" +
        "</gml:coordinates></gml:LineString>, xs:double(0))",
        "<gml:Polygon xmlns:gml=\"http://www.opengis.net/gml\">" +
        "<gml:outerBoundaryIs><gml:LinearRing><gml:coordinates/>" +
        "</gml:LinearRing></gml:outerBoundaryIs></gml:Polygon>");
    error("geo:buffer(" +
            "<gml:LinearRing><gml:coordinates>1,1 55,99 2,1</gml:coordinates>" +
            "</gml:LinearRing>, xs:double(1))", GeoErrors.qname(2));
    error("geo:buffer(<gml:LinearRing><gml:coordinates>1,1 55,99 1,1" +
            "</gml:coordinates></gml:LinearRing>, 's')", EXPTYPE_X_X_X.qname());
    error("geo:buffer(xs:double(1))", FUNCARGNUM_X_X_X.qname());
  }