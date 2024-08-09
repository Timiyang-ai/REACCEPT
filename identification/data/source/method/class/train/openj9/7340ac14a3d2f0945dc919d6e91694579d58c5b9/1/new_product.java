public boolean enqueue() {
	/*[IF Sidecar19-SE]*/
	if (ClearBeforeEnqueue.ENABLED) {
		clear();
	}
	/*[ENDIF]*/
	return enqueueImpl();
}