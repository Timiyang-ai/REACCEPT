public T evolve(int generation, double threshold) {
        if (generation <= 0) {
            throw new IllegalArgumentException("Invalid number of generations to go: " + generation);
        }
        
        // Calculate the fitness of each chromosome.
        try {
            MulticoreExecutor.run(tasks);
        } catch (Exception ex) {
            logger.error("Failed to run Genetic Algorithm on multi-core", ex);

            for (Task task : tasks) {
                task.call();
            }
        }

        Arrays.sort(population);
        T best = population[size-1];

        Chromosome[] offsprings = new Chromosome[size];
        for (int g = 1; g <= generation && best.fitness() < threshold; g++) {
            for (int i = 0; i < elitism; i++) {
                offsprings[i] = population[size-i-1];
            }

            for (int i = elitism; i < size; i+=2) {
                T father = select(population);
                T mother = select(population);
                while (mother == father) {
                    mother = select(population);
                }

                Chromosome[] children = father.crossover(mother);
                offsprings[i] = children[0];
                offsprings[i].mutate();
                if (i + 1 < size) {
                    offsprings[i + 1] = children[1];
                    offsprings[i + 1].mutate();
                }
            }

            System.arraycopy(offsprings, 0, population, 0, size);

            try {
                MulticoreExecutor.run(tasks);
            } catch (Exception ex) {
                logger.error("Failed to run Genetic Algorithm on multi-core", ex);

                for (Task task : tasks) {
                    task.call();
                }
            }

            Arrays.sort(population);
            best = population[size - 1];
            
            double avg = 0.0;
            for (Chromosome ch : population) {
                avg += ch.fitness();
            }
            avg /= size;

            logger.info(String.format("Genetic Algorithm: generation %d, best fitness %G, average fitness %G", g, best.fitness(), avg));
        }

        return best;
    }