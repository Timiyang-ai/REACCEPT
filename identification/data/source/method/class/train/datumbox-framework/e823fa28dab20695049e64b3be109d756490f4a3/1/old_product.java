public static double euclideanWeighhted(AssociativeArray a1, AssociativeArray a2, Map<Object, Double> columnWeights) {
        Map<Object, Double> columnDistances = columnDistances(a1, a2, columnWeights.keySet());
        
        double distance = 0.0;
        for(Map.Entry<Object, Double> entry : columnDistances.entrySet()) {
            double columnDistance = entry.getValue();
            distance+=(columnDistance*columnDistance)*columnWeights.get(entry.getKey());
        }
        return Math.sqrt(distance);
    }