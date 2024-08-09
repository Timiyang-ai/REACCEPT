@Test
	public void orderNodeGrid() {
		SquareGridTools alg = new SquareGridTools();

		for( int rows = 1; rows <= 4; rows++ ) {
			for (int cols = 1; cols <= 4; cols++) {
				if( rows == 1 && cols == 1 )
					continue;

				SquareGrid grid = createGrid(rows, cols);

//				System.out.println("grid shape "+rows+" "+cols);

				for (int flip = 0; flip < 2; flip++) {
					for (int rotate = 0; rotate < 4; rotate++) {

//						System.out.println("  flip "+flip+" rotate "+rotate);
						for (int i = 0; i < rows; i++) {
							for (int j = 0; j < cols; j++) {
//								System.out.println("    element "+i+" "+j);
								alg.orderNodeGrid(grid, i, j);
								check_orderNodeGrid(alg.ordered,grid.get(i,j).center);
							}
						}
						for (int i = 0; i < rows; i++) {
							for (int j = 0; j < cols; j++) {
								UtilPolygons2D_F64.shiftDown(grid.get(i,j).corners);
							}
						}
					}
					for (int i = 0; i < rows; i++) {
						for (int j = 0; j < cols; j++) {
							UtilPolygons2D_F64.flip(grid.get(i, j).corners);
						}
					}
				}

			}
		}
	}