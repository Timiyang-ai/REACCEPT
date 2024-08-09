protected static void convert( LineGeneral2D_F64[] lines , Quadrilateral_F64 quad ) {

		if( null == Intersection2D_F64.intersection(lines[0],lines[1],quad.a) )
			throw new RuntimeException("Oh crap");
		if( null == Intersection2D_F64.intersection(lines[2],lines[1],quad.b) )
			throw new RuntimeException("Oh crap");
		if( null == Intersection2D_F64.intersection(lines[2],lines[3],quad.c) )
			throw new RuntimeException("Oh crap");
		if( null == Intersection2D_F64.intersection(lines[0],lines[3],quad.d) )
			throw new RuntimeException("Oh crap");
	}