@Test
  public void interiorRingN() {
	  
	  runQuery("geo:interiorRingN(<gml:Polygon><gml:outerBoundaryIs><gml:LinearRing>" +
	  		"<gml:coordinates>11,11 18,11 18,18 11,18 11,11</gml:coordinates>" +
	  		"</gml:LinearRing></gml:outerBoundaryIs><gml:innerBoundaryIs><gml:LinearRing>" +
	  		"<gml:coordinates>2,2 3,2 3,3 2,3 2,2</gml:coordinates></gml:LinearRing>" +
	  		"</gml:innerBoundaryIs><gml:innerBoundaryIs><gml:LinearRing><gml:coordinates>" +
	  		"10,10 20,10 20,20 10,20 10,10</gml:coordinates></gml:LinearRing>" +
	  		"</gml:innerBoundaryIs></gml:Polygon>, 1)",
		  		"<gml:LineString srsName=\"0\"><gml:coordinates>" +
		  		"2.0,2.0 3.0,2.0 3.0,3.0 2.0,3.0 2.0,2.0</gml:coordinates>" +
		  		"</gml:LineString>");
	  
	  runError("geo:interiorRingN(<gml:Polygon><gml:outerBoundaryIs><gml:LinearRing>" +
		  		"<gml:coordinates>11,11 18,11 18,18 11,18 11,11</gml:coordinates>" +
		  		"</gml:LinearRing></gml:outerBoundaryIs></gml:Polygon>, 1)",
			  		new QNm("GEO0006"));
	  
	  runError("geo:interiorRingN(<gml:Polygon><gml:outerBoundaryIs><gml:LinearRing>" +
	  		"<gml:coordinates>11,11 18,11 18,18 11,18 11,11</gml:coordinates>" +
	  		"</gml:LinearRing></gml:outerBoundaryIs></gml:Polygon>, 0)",
		  		new QNm("GEO0006"));
	  
	  runError("geo:interiorRingN(<gml:Point><gml:coordinates>2.0,3.0</gml:coordinates></gml:Point>, 1)",
			  new QNm("GEO0003"));
	  
	  runError("geo:interiorRingN(text {'<gml:Polygon/'}, 1)", new QNm("FORG0006"));
	  runError("geo:interiorRingN()", new QNm("XPST0017"));
  }