@Test
  public void boundary() {
    run("geo:boundary(<gml:Polygon><gml:outerBoundaryIs><gml:LinearRing>" +
        "<gml:coordinates>11,11 18,11 18,18 11,18 11,11</gml:coordinates>" +
        "</gml:LinearRing></gml:outerBoundaryIs></gml:Polygon>)",
        "<gml:LineString xmlns:gml=\"http://www.opengis.net/gml\">" +
        "<gml:coordinates>11.0,11.0 18.0,11.0 18.0,18.0 11.0,18.0 " +
        "11.0,11.0</gml:coordinates></gml:LineString>");

    run("geo:boundary(" +
        "<gml:Point><gml:coordinates>2,3</gml:coordinates></gml:Point>)",
        "<gml:MultiGeometry xmlns:gml=\"http://www.opengis.net/gml\"/>");
    error("geo:boundary(text {'a'})", FUNTYPE.qname());
    error("geo:boundary(a)", NOCTX.qname());
    error("geo:boundary(<gml:geo/>)", GeoErrors.qname(1));
    error("geo:boundary(<gml:Point><gml:pos>1 2</gml:pos></gml:Point>)",
        GeoErrors.qname(2));
  }