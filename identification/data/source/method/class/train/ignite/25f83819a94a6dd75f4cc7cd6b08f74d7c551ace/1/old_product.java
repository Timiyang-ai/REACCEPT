public LearningEnvironmentBuilder withParallelismStrategy(ParallelismStrategy.Type stgyType) {
        switch (stgyType) {
            case NO_PARALLELISM:
                this.parallelismStgy = NoParallelismStrategy.INSTANCE;
            case ON_DEFAULT_POOL:
                this.parallelismStgy = new DefaultParallelismStrategy();
        }
        return this;
    }