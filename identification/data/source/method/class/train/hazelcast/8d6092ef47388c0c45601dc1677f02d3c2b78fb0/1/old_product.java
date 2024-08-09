public static Permission getPermission(String name, String serviceName, String... actions) {
        if (QueueService.SERVICE_NAME.equals(serviceName)) {
            return new QueuePermission(name, actions);
        } else if (MapService.SERVICE_NAME.equals(serviceName)) {
            return new MapPermission(name, actions);
        } else if (MultiMapService.SERVICE_NAME.equals(serviceName)) {
            return new MultiMapPermission(name, actions);
        } else if (ListService.SERVICE_NAME.equals(serviceName)) {
            return new ListPermission(name, actions);
        } else if (SetService.SERVICE_NAME.equals(serviceName)) {
            return new SetPermission(name, actions);
        } else if (AtomicLongService.SERVICE_NAME.equals(serviceName)) {
            return new AtomicLongPermission(name, actions);
        } else if (CountDownLatchService.SERVICE_NAME.equals(serviceName)) {
            return new CountDownLatchPermission(name, actions);
        } else if (SemaphoreService.SERVICE_NAME.equals(serviceName)) {
            return new SemaphorePermission(name, actions);
        } else if (TopicService.SERVICE_NAME.equals(serviceName)) {
            return new TopicPermission(name, actions);
        } else if (LockService.SERVICE_NAME.equals(serviceName)) {
            return new LockPermission(name, actions);
        } else if (DistributedExecutorService.SERVICE_NAME.equals(serviceName)) {
            return new ExecutorServicePermission(name, actions);
        } else if (IdGeneratorService.SERVICE_NAME.equals(serviceName)) {
            return new AtomicLongPermission(IdGeneratorService.ATOMIC_LONG_NAME + name, actions);
        } else if (MapReduceService.SERVICE_NAME.equals(serviceName)) {
            return new MapReducePermission(name, actions);
        } else if (ReplicatedMapService.SERVICE_NAME.equals(serviceName)) {
            return new ReplicatedMapPermission(name, actions);
        } else if (AtomicReferenceService.SERVICE_NAME.equals(serviceName)) {
            return new AtomicReferencePermission(name, actions);
        }
        throw new IllegalArgumentException("No service matched!!!");
    }