public static int compute(double frequency,
                              int previousScore,
                              int checkmarkValue)
    {
        double multiplier = Math.pow(0.5, 1.0 / (14.0 / frequency - 1));
        int score = (int) (previousScore * multiplier);

        if (checkmarkValue == Checkmark.CHECKED_EXPLICITLY)
        {
            score += 1000000;
            score = Math.min(score, Score.MAX_VALUE);
        }

        return score;
    }