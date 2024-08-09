synchronized int touchOne(int generation) {
        removeFromGeneration(generation);
        addToCurrentGeneration();
        return this.currentGeneration;
    }