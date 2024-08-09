public boolean touchDown (int screenX, int screenY, int pointer, int button) {
		if (screenX < viewport.getViewportX() || screenX >= viewport.getViewportX() + viewport.getViewportWidth()) return false;
		if (Gdx.graphics.getHeight() - screenY < viewport.getViewportY()
			|| Gdx.graphics.getHeight() - screenY >= viewport.getViewportY() + viewport.getViewportHeight()) return false;

		pointerTouched[pointer] = true;
		pointerScreenX[pointer] = screenX;
		pointerScreenY[pointer] = screenY;

		screenToStageCoordinates(stageCoords.set(screenX, screenY));

		InputEvent event = Pools.obtain(InputEvent.class);
		event.setType(Type.touchDown);
		event.setStage(this);
		event.setStageX(stageCoords.x);
		event.setStageY(stageCoords.y);
		event.setPointer(pointer);
		event.setButton(button);

		Actor target = hit(stageCoords.x, stageCoords.y, true);
		if (target == null) target = root;

		target.fire(event);
		boolean handled = event.isHandled();
		Pools.free(event);
		return handled;
	}