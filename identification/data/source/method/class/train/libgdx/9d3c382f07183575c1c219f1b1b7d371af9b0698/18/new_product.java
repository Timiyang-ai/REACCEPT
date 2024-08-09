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
		while (target != null && (!target.isTouchable() || !target.isVisible()))
			target = target.getParent();
		if (target == null) target = root;

		target.fire(event);
		Pools.free(event);
		return event.isHandled();
	}