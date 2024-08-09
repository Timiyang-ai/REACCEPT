@Override
	public boolean touchDown (int x, int y, int pointer, int button) {
		setKeyboardFocus(null);
		Actor actor = touchFocus[pointer];
		if (actor == null) actor = root;
		toStageCoordinates(x, y, coords);
		Group.toChildCoordinates(actor, coords.x, coords.y, point);
		return actor.touchDown(point.x, point.y, pointer);
	}