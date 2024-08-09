public static double contextual (double target,
                                     double source,
                                     double ratio)
    {
        return (source * support(target, ratio)) + ((1 - source) * target);
    }