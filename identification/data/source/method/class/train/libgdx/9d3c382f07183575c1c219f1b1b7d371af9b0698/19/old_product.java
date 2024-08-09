public boolean touchDown (int screenX, int screenY, int pointer, int button) {
		screenToStageCoordinates(stageCoords.set(screenX, screenY));

		ActorEvent event = Pools.obtain(ActorEvent.class);
		event.setType(Type.touchDown);
		event.setStage(this);
		event.setStageX(stageCoords.x);
		event.setStageY(stageCoords.y);
		event.setPointer(pointer);
		event.setButton(button);

		Actor target = hit(stageCoords.x, stageCoords.y);
		if (target == null) target = root;

		target.fire(event);
		boolean handled = event.isHandled();
		Pools.free(event);
		return handled;
	}