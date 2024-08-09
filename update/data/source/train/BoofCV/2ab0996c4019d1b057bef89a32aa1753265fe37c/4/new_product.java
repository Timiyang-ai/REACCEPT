void scoreDirection(int dx , int dy ) {

		// Create a list of paths it will score
		trajectories.reset();
		if( dx > 0 ) {
			for (int y = 0; y < lengthY; y++) {
				trajectories.grow().set(0,y,dx,dy);
			}
		} else if( dx < 0 ) {
			for (int y = 0; y < lengthY; y++) {
				trajectories.grow().set(effectiveLengthX-1,y,dx,dy);
			}
		}
		if( dy > 0 ) {
			int x0 = 0, x1 = effectiveLengthX;
			if( dx > 0) x0 += 1;
			if( dx < 0) x1 -= 1;
			for (int x = x0; x < x1; x++) {
				trajectories.grow().set(x,0,dx,dy);
			}
		} else if( dy < 0 ) {
			int x0 = 0, x1 = effectiveLengthX;
			if( dx > 0) x0 += 1;
			if( dx < 0) x1 -= 1;
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