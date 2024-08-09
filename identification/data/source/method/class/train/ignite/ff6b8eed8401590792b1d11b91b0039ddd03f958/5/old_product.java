public LearningEnvironmentBuilder withParallelismStrategy(ParallelismStrategy.Type stgyType) {
        switch (stgyType) {
            case NO_PARALLELISM:
                this.parallelismStgy = NoParallelismStrategy.INSTANCE;
                break;
            case ON_DEFAULT_POOL:
                this.parallelismStgy = new DefaultParallelismStrategy();
                break;
        }
        return this;
    }