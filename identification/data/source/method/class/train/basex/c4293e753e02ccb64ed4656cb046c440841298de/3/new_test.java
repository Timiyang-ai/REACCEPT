@Test
  public void getSRID() {
    runQuery("geo:getSRID(<gml:Polygon srsName=" +
            "\"http://www.opengis.net/gml/srs/epsg.xml#4326\">" +
            "<outerboundaryIs><gml:LinearRing><coordinates>" +
            "-150,50 -150,60 -125,60 -125,50 -150,50" +
            "</coordinates></gml:LinearRing></outerboundaryIs></gml:Polygon>)", "0");

    runError("geo:getSRID(text {'a'})", new QNm("FORG0006"));
    runError("geo:getSRID(<gml:unknown/>)", new QNm("GEO0001"));
    runError("geo:getSRID(<gml:LinearRing><gml:pos>1,1 20,1 50,30 1,1</gml:pos>" +
            "</gml:LinearRing>)", new QNm("GEO0002"));
  }