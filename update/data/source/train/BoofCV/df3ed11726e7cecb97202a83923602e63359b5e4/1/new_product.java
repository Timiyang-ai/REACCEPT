public boolean refine(Quadrilateral_F64 input, Quadrilateral_F64 output)
	{
		// find center to use as local coordinate system.  Improves numerics slightly
		UtilPolygons2D_F64.center(input,center);

		// estimate line equations
		return optimize(input,output);
	}