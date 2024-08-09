public T evolve(int generation, double threshold) {
        if (generation <= 0) {
            throw new IllegalArgumentException("Invalid number of generations to go: " + generation);
        }
        
        // Calculate the fitness of each chromosome.
        Arrays.stream(population).parallel().forEach(chromosome -> {
            if (chromosome instanceof LamarckianChromosome) {
                LamarckianChromosome ch = (LamarckianChromosome) chromosome;
                for (int j = 0; j < t; j++) {
                    ch.evolve();
                }
            }

            chromosome.fitness();
        });

        Arrays.sort(population);
        T best = population[size-1];

        Chromosome[] offsprings = new Chromosome[size];
        for (int g = 1; g <= generation && best.fitness() < threshold; g++) {
            for (int i = 0; i < elitism; i++) {
                offsprings[i] = population[size-i-1];
            }

            for (int i = elitism; i < size; i+=2) {
                T father = selection.apply(population);
                T mother = selection.apply(population);
                while (mother == father) {
                    mother = selection.apply(population);
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

            // Calculate the fitness of each chromosome.
            Arrays.stream(population).parallel().forEach(chromosome -> {
                if (chromosome instanceof LamarckianChromosome) {
                    LamarckianChromosome ch = (LamarckianChromosome) chromosome;
                    for (int j = 0; j < t; j++) {
                        ch.evolve();
                    }
                }

                chromosome.fitness();
            });

            Arrays.sort(population);
            best = population[size - 1];
            
            double avg = 0.0;
            for (Chromosome ch : population) {
                avg += ch.fitness();
            }
            avg /= size;

            logger.info(String.format("Generation %d, best fitness %G, average fitness %G", g, best.fitness(), avg));
        }

        return best;
    }