    private <Message> ActorRef<Message> getChild(Supervisor sup, String name, long timeout) throws InterruptedException, SuspendExecution {
        return (ActorRef<Message>) sup.getChild(name);
//        Actor<Message, V> a;
//        final long start = System.nanoTime();
//        while ((a = sup.getChild(name)) == null || a.isDone()) {
//            if (System.nanoTime() > start + TimeUnit.MILLISECONDS.toNanos(timeout))
//                return null;
//            Thread.sleep(10);
//        }
//        return a;
    }