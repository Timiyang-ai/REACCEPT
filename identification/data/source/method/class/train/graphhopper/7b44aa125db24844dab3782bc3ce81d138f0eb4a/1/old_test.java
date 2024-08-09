    private static List<List<Double>> createStartPoints(List<Instruction> instructions) {
        List<List<Double>> res = new ArrayList<>(instructions.size());
        for (Instruction instruction : instructions) {
            res.add(Arrays.asList(instruction.getPoints().getLatitude(0), instruction.getPoints().getLongitude(0)));
        }
        return res;
    }