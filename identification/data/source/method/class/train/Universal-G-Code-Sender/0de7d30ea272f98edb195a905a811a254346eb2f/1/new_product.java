public static String generateJogCommand(String command, Units units,
                                            double distance, double feedRate, int dirX, int dirY, int dirZ) {
        return generateJogCommand(unitCommand(units) + command, units, distance, feedRate, dirX, dirY, dirZ, units);
    }