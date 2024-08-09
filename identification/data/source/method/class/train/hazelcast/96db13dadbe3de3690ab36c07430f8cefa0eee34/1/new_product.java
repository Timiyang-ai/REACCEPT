public QueueConfig setName(String name) {
        this.name = name;
        if (backingMapName == null) {
            backingMapName = "q:" + name;
        }
        return this;
    }