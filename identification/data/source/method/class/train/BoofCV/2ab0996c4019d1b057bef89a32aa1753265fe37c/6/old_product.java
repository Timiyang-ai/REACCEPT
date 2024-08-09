void scoreDirection(int dx , int dy ) {

		// Create a list of paths it will score
		trajectories.reset();
		if( dx > 0 ) {
			for (int y = 0; y < lengthY; y++) {
				trajectories.grow().set(0,y,dx,dy);
			}
		} else if( dx < 0 ) {
			for (int y = 0; y < lengthY; y++) {
				trajectories.grow().set(lengthX-1,y,dx,dy);
			}
		}
		if( dy > 0 ) {
			int x0 = dx > 0 ? 1 : 0;
			int x1 = dx < 0 ? lengthX-1 : lengthX;
			for (int x = x0; x < x1; x++) {
				trajectories.grow().set(x,0,dx,dy);
			}
		} else if( dy < 0 ) {
			int x0 = dx > 0 ? 1 : 0;
			int x1 = dx < 0 ? lengthX-1 : lengthX;
			for (int x = x0; x < x1; x++) {
				trajectories.grow().set(x,lengthY-1,dx,dy);
			}
		}

		if( BoofConcurrency.USE_CONCURRENT ) {
			BoofConcurrency.loopBlocks(0,trajectories.size,1,workspace,computeBlock);
		} else {
			WorkSpace w= workspace.get(0);
			w.checkSize();
			for (int i = 0; i < trajectories.size; i++) {
				Trajectory t = trajectories.get(i);
				scorePath(t.x0, t.y0, t.dx, t.dy, w.workCostLr);
			}
		}
	}