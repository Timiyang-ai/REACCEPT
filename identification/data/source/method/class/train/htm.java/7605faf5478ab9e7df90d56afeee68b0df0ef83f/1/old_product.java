public static TIntArrayList getNeighborsND(SpatialLattice l, int columnIndex, int radius, boolean wrapAround) {
        final int[] dimensions = l.getInputDimensions();
        int[] columnCoords = l.getInputMatrix().computeCoordinates(columnIndex);
        List<int[]> dimensionCoords = new ArrayList<int[]>();
        for(int i = 0;i < dimensions.length;i++) {
            int[] range = ArrayUtils.range(columnCoords[i] - radius, columnCoords[i] + radius + 1);
            int[] curRange = new int[range.length];
            
            if(wrapAround) {
                for(int j = 0;j < curRange.length;j++) {
                    curRange[j] = (int)ArrayUtils.positiveRemainder(range[j], dimensions[i]);
                }
            }else{
                final int idx = i;
                curRange = range;
                curRange = ArrayUtils.retainLogicalAnd(range, 
                    new Condition[] {
                        new Condition.Adapter() {
                            @Override public boolean eval(int n) { return n >= 0; }
                        },
                        new Condition.Adapter() {
                            @Override public boolean eval(int n) { return n < dimensions[idx]; }
                        }
                    }
                );
            }
            dimensionCoords.add(ArrayUtils.unique(curRange));
        }
        
        List<TIntList> neighborList = ArrayUtils.dimensionsToCoordinateList(dimensionCoords);
        TIntArrayList neighbors = new TIntArrayList(neighborList.size());
        for(int i = 0;i < neighborList.size();i++) {
            int flatIndex = l.getInputMatrix().computeIndex(neighborList.get(i).toArray());
            if(flatIndex == columnIndex) continue;
            neighbors.add(flatIndex);
        }
        return neighbors;
    }