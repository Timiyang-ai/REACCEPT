protected static void convert( LineGeneral2D_F64[] lines , Quadrilateral_F64 quad ) {

		LineGeneral2D_F64 l0 = lines[0];
		LineGeneral2D_F64 l1=null,l2=null,l3=null;

		double angle1 = angle(lines[0],lines[1]);
		double angle2 = angle(lines[0],lines[2]);
		double angle3 = angle(lines[0],lines[3]);

		if( angle1 > angle2 ) {
			l1 = lines[1];
			if( angle2 > angle3 ) {
				l2 = lines[2];
				l3 = lines[3];
			} else {
				l3 = lines[2];
				l2 = lines[3];
			}
		} else {
			l1 = lines[2];

			if( angle1 > angle3 ) {
				l2 = lines[1];
				l3 = lines[3];
			} else {
				l3 = lines[1];
				l2 = lines[3];
			}
		}

		if( null == Intersection2D_F64.intersection(l0,l1,quad.a) )
			throw new RuntimeException("Oh crap");
		if( null == Intersection2D_F64.intersection(l0,l2,quad.b) )
			throw new RuntimeException("Oh crap");
		if( null == Intersection2D_F64.intersection(l3,l2,quad.c) )
			throw new RuntimeException("Oh crap");
		if( null == Intersection2D_F64.intersection(l3,l1,quad.d) )
			throw new RuntimeException("Oh crap");
	}