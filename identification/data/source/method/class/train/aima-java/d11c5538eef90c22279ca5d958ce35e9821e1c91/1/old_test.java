    @Test
    public void currentBestLearningTest() {
        CurrentBestLearning algo = new CurrentBestLearning();
        HashMap<String, String> initial = new HashMap<>();
        initial.put("Alt", "Yes");
        Hypothesis initialHypothesis = new Hypothesis("WillWait", new ArrayList<>(Collections.singletonList(initial)));
        Hypothesis result = algo.currentBestLearning(KnowledgeLearningProblemFactory.getRestaurantLogicalExample(), initialHypothesis, new ArrayList<>());
        List<LogicalExample> examples = KnowledgeLearningProblemFactory.getRestaurantLogicalExample();
        int correct = 0;
        int wrong = 0;
        for (LogicalExample example : examples) {
            Assert.assertEquals(example.getGoal(), result.predict(example));
            if (example.getGoal() == result.predict(example))
                correct++;
            else
                wrong++;
        }
        Assert.assertEquals(12, correct);
        Assert.assertEquals(0, wrong);
    }