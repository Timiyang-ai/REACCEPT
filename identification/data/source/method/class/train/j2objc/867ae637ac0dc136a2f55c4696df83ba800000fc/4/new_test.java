    private void enqueue(ReferenceQueue<Object> queue) {
        new WeakReference<Object>(new Object(), queue).enqueue();
    }