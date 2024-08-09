public static double contextual (double target,
                                     double ratio,
                                     double source)
    {
        return (source * support(target, ratio)) + ((1 - source) * target);
    }