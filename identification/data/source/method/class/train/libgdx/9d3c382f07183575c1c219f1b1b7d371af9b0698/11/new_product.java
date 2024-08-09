public boolean touchDown (int screenX, int screenY, int pointer, int button) {
		ActorEvent event = Pools.obtain(ActorEvent.class);
		event.setPointer(pointer);
		event.setButton(button);
		Vector2 stageCoords = toStageCoordinates(screenX, screenY);
		return fireTouch(event, ActorEvent.Type.touchDown, stageCoords.x, stageCoords.y);
	}