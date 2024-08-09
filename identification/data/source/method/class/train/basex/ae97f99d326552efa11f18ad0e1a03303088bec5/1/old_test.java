@Test
  public void isClosed() {
	  
	  runQuery("geo:isClosed(<gml:LinearRing><gml:coordinates>2,3 20,1 20,20 2,3" +
	  		"</gml:coordinates></gml:LinearRing>)",
			  "true");
	  runQuery("geo:isClosed(<gml:MultiLineString><gml:LineString><gml:coordinates>" +
	  		"1,1 0,0 2,1</gml:coordinates></gml:LineString><gml:LineString>" +
	  		"<gml:coordinates>2,1 3,3 4,4</gml:coordinates></gml:LineString>" +
	  		"</gml:MultiLineString>)",
		  		"false");
	  
	  runQuery("geo:isClosed(<gml:LineString><gml:coordinates>11,10 20,1 20,20 12,13" +
		  		"</gml:coordinates></gml:LineString>)", "false");
	 
	  runError("geo:isClosed(<gml:Polygon><gml:outerBoundaryIs><gml:LinearRing>" +
	  		"<gml:coordinates>1,1 2,1 5,3 1,1</gml:coordinates></gml:LinearRing>" +
	  		"</gml:outerBoundaryIs></gml:Polygon>)",
	  		new QNm("GEO0004"));
	  
	  runError("geo:isClosed(<gml:LineString><gml:coordinates>1,1</gml:coordinates></gml:LineString>)",
			  		new QNm("GEO0002"));
	  runError("geo:isClosed()", new QNm("XPST0017"));
	  runError("geo:isClosed(text {'gml:Point'})", new QNm("FORG0006"));
	  runError("geo:isClosed(<Point><gml:coordinates>2,1</gml:coordinates></Point>)",
		  		new QNm("GEO0001"));
  }