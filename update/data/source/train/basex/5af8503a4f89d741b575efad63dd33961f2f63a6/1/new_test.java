@Test
  public void srid() {
    runQuery("geo:srid(<gml:Polygon srsName=" +
            "\"http://www.opengis.net/gml/srs/epsg.xml#4326\">" +
            "<outerboundaryIs><gml:LinearRing><coordinates>" +
            "-150,50 -150,60 -125,60 -125,50 -150,50" +
            "</coordinates></gml:LinearRing></outerboundaryIs></gml:Polygon>)", "0");

    runError("geo:srid(text {'a'})", FUNCMP.qname());
    runError("geo:srid(<gml:unknown/>)", GeoErrors.qname(1));
    runError("geo:srid(<gml:LinearRing><gml:pos>1,1 20,1 50,30 1,1</gml:pos>" +
            "</gml:LinearRing>)", GeoErrors.qname(2));
  }