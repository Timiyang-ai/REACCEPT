public int[] getNeighborsND(SparseMatrix<Column> poolerMem, int columnIndex, int radius, boolean wrapAround) {
		int[] columnCoords = poolerMem.computeCoordinates(columnIndex);
		List<int[]> dimensionCoords = new ArrayList<int[]>();
		for(int i = 0;i < inputDimensions.length;i++) {
			int[] range = ArrayUtils.range(columnCoords[0] - radius, columnCoords[0] + radius + 1);
			int[] curRange = new int[range.length];
			
			if(wrapAround) {
				for(int j = 0;j < curRange.length;j++) {
					curRange[j] = (int)ArrayUtils.positiveRemainder(range[j], inputDimensions[i]);
				}
			}else{
				curRange = range;
			}
			
			dimensionCoords.add(ArrayUtils.unique(curRange));
		}
		
		List<TIntList> neighborList = ArrayUtils.dimensionsToCoordinateList(dimensionCoords);
		TIntList neighbors = new TIntArrayList(neighborList.size());
		for(int i = 0;i < neighborList.size();i++) {
			int flatIndex = poolerMem.computeIndex(neighborList.get(i).toArray());
			if(flatIndex == columnIndex) continue;
			neighbors.add(flatIndex);
		}
		return neighbors.toArray();
	}