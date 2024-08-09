@Override
	public boolean touchDown (int x, int y, int pointer, int button) {
		toStageCoordinates(x, y, coords);
		Group.toChildCoordinates(root, coords.x, coords.y, point);
		return root.touchDown(point.x, point.y, pointer);
	}