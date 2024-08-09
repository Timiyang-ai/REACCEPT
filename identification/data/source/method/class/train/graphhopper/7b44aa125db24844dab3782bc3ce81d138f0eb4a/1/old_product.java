List<List<Double>> createStartPoints() {
        List<List<Double>> res = new ArrayList<List<Double>>(instructions.size());
        for (Instruction instruction : instructions) {
            res.add(Arrays.asList(instruction.getFirstLat(), instruction.getFirstLon()));
        }
        return res;
    }