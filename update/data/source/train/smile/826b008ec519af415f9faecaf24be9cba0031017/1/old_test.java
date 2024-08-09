@Test
    public void testEvolve() {
        System.out.println("evolve");
        BitString[] seeds = new BitString[100];
        
        // The mutation parameters are set higher than usual to prevent premature convergence. 
        for (int i = 0; i < seeds.length; i++) {
            seeds[i] = new BitString(15, new Knapnack(), BitString.Crossover.UNIFORM, 1.0, 0.2);
        }
        
        GeneticAlgorithm<BitString> instance = new GeneticAlgorithm<>(seeds, GeneticAlgorithm.Selection.TOURNAMENT);
        instance.setElitism(2);
        instance.setTournament(3, 0.95);
        
        BitString result = instance.evolve(1000, 18);
        assertEquals(18, result.fitness(), 1E-7);

        int[] best = {1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        for (int i = 0; i < best.length; i++) {
            assertEquals(best[i], result.bits()[i]);
        }
        
    }