public FlatDataList extractColumnValues(Object column) {
        FlatDataList flatDataList = new FlatDataList();
        
        for(Integer rId : this) {
            Record r = recordList.get(rId);
            flatDataList.add(r.getX().get(column));
        }
        
        return flatDataList;
    }