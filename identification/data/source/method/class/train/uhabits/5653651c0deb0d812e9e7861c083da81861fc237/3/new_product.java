public static double compute(double frequency,
                                 double previousScore,
                                 double checkmarkValue)
    {
        double multiplier = pow(0.5, frequency / 13.0);

        double score = previousScore * multiplier;
        score += checkmarkValue * (1 - multiplier);

        return score;
    }