protected void orderNodeGrid(SquareGrid grid, int row, int col) {
		SquareNode node = grid.get(row,col);

		if(grid.rows==1 && grid.columns==1 ) {
			for (int i = 0; i < 4; i++) {
				ordered[i] = node.corners.get(i);
			}
		} else if( grid.columns==1 ) {
			if (row == grid.rows - 1) {
				orderNode(node, grid.get(row - 1, col), false);
				rotateTwiceOrdered();
			} else {
				orderNode(node, grid.get(row + 1, col), false);
			}
		} else {
			if( col == grid.columns-1) {
				orderNode(node, grid.get(row, col-1), true);
				rotateTwiceOrdered();
			} else {
				orderNode(node, grid.get(row, col+1), true);
			}
		}
	}