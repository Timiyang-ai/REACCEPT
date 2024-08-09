private void skipNonOpcodes() {
		while (cursor != null && (cursor.getType() == AbstractInsnNode.FRAME
				|| cursor.getType() == AbstractInsnNode.LABEL
				|| cursor.getType() == AbstractInsnNode.LINE)) {
			cursor = cursor.getNext();
		}
	}