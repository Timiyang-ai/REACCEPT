public QueueConfig setName(String name) {
        this.name = name;
        if (backingMapRef == null) {
            backingMapRef = "q:" + name;
        }
        return this;
    }