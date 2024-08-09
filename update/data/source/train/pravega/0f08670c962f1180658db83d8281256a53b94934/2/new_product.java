synchronized int touchOne(int generation) {
        removeOne(generation);
        addOne();
        return this.currentGeneration;
    }