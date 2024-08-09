@Test
	public void test_enqueue() {
		ReferenceQueue rq = new ReferenceQueue();
		obj = new Object();
		Reference ref = new SoftReference(obj, rq);
		AssertJUnit.assertTrue("Enqueue failed.", (!ref.isEnqueued()) && ((ref.enqueue()) && (ref.isEnqueued())));
		if (isJava8 || disableClearBeforeEnqueue) {
			AssertJUnit.assertTrue("Not properly enqueued.", rq.poll().get() == obj);
		} else {
			AssertJUnit.assertTrue("Not properly enqueued.", rq.poll().get() == null);
		}
		
		AssertJUnit.assertTrue("Should remain enqueued.", !ref.isEnqueued()); //This fails.
		AssertJUnit.assertTrue("Can not enqueue twice.", (!ref.enqueue()) && (rq.poll() == null));

		rq = new ReferenceQueue();
		obj = new Object();
		ref = new WeakReference(obj, rq);
		AssertJUnit.assertTrue("Enqueue failed2.", (!ref.isEnqueued()) && ((ref.enqueue()) && (ref.isEnqueued())));
		if (isJava8 || disableClearBeforeEnqueue) {
			AssertJUnit.assertTrue("Not properly enqueued2.", rq.poll().get() == obj);
		} else {
			AssertJUnit.assertTrue("Not properly enqueued2.", rq.poll().get() == null);
		}
		AssertJUnit.assertTrue("Should remain enqueued2.", !ref.isEnqueued()); //This fails.
		AssertJUnit.assertTrue("Can not enqueue twice2.", (!ref.enqueue()) && (rq.poll() == null));
	}