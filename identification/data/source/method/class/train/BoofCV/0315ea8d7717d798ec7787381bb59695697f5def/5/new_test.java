@Test
	public void detect_concave() {
		List<Polygon2D_F64> polygons = new ArrayList<>();
		Polygon2D_F64 expected = new Polygon2D_F64(20, 20, 20, 80, 40, 40, 80, 80, 80, 20);
		polygons.add(expected);

		for( Class imageType : imageTypes ) {
			renderPolygons(polygons,imageType );

			BinaryPolygonDetector alg = createDetector(imageType, true, 5,5);
			alg.setConvex(false);

			alg.process(dist, binary);

			assertEquals(1, alg.getFoundPolygons().size());

			Polygon2D_F64 found = (Polygon2D_F64)alg.getFoundPolygons().get(0);
			assertEquals(1, findMatches(found, 1));
		}
	}