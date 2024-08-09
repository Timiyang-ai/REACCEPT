@Test
  public void geometryN() {
	  
	  runQuery("geo:geometryN(<gml:Point><gml:coordinates>2,1</gml:coordinates></gml:Point>, 1)",
		  		"<gml:Point srsName=\"0\"><gml:coordinates>2.0,1.0</gml:coordinates></gml:Point>");
	  
	  runError("geo:geometryN(<gml:unknown><gml:coordinates>1,1</gml:coordinates></gml:unknown>,1)",
		  		new QNm("GEO0001"));
	  runError("geo:geometryN(<gml:Point><gml:coordinates>2,1</gml:coordinates></gml:Point>, 0)",
		  		new QNm("GEO0006"));
	  runError("geo:geometryN(<gml:Point><gml:coordinates>2,1</gml:coordinates></gml:Point>, 2)",
		  		new QNm("GEO0006"));
	  runError("geo:geometryN(text {'a'}, <gml:Point><gml:coordinates>2,3" +
	  		"</gml:coordinates></gml:Point>)", new QNm("FORG0006"));
  }