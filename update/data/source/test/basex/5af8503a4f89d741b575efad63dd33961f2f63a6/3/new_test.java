@Test
  public void z() {
    runQuery("geo:z(<gml:Point><gml:coordinates>2,1,3</gml:coordinates></gml:Point>)",
        "3");
    runQuery("geo:z(<gml:Point><gml:coordinates>2</gml:coordinates></gml:Point>)",
        "NaN");

    runError("geo:z(<gml:MultiPoint><gml:Point><gml:coordinates>1,1" +
            "</gml:coordinates></gml:Point><gml:Point><gml:coordinates>1,2" +
        "</gml:coordinates></gml:Point></gml:MultiPoint>)", GeoErrors.qname(3));
    runError("geo:z(" +
        "<gml:LinearRing><gml:coordinates>0,0 20,0 0,20 0,0" +
        "</gml:coordinates></gml:LinearRing>)", GeoErrors.qname(3));
    runError("geo:z(<gml:Point><gml:coordinates></gml:coordinates></gml:Point>)",
            GeoErrors.qname(2));
    runError("geo:z(<gml:geo><gml:coordinates>2,1</gml:coordinates></gml:geo>)",
        GeoErrors.qname(1));
    runError("geo:z(a)", XPNOCTX.qname());
  }