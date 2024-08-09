public static double median(AssociativeArray2D survivalFunction) throws IllegalArgumentException {
        Double ApointTi = null;
        Double BpointTi = null;
        
        int n = survivalFunction.size();
        if(n==0) {
            throw new IllegalArgumentException();
        } 
        
        for(Map.Entry<Object, AssociativeArray> entry : survivalFunction.entrySet()) {
            Object ti = entry.getKey();
            AssociativeArray row = entry.getValue();
            
            Double Sti = row.getDouble("Sti");
            
            if(Sti==null) {
                continue; //skip censored internalData
            }
            
            Double point = Double.valueOf(ti.toString());
            if(Sti==0.5) {
                return point; //we found extactly the point
            }
            else if(Sti>0.5) {
                ApointTi=point; //keep the point just before the 0.5 probability
            }
            else {
                BpointTi=point; //keep the first point after the 0.5 probability and exit loop
                break;
            }
        }
        
        if(n==1) {
            return (ApointTi!=null)?ApointTi:BpointTi;
        }
        
        double ApointTiValue = TypeInference.toDouble(survivalFunction.get2d(ApointTi.toString(), "Sti"));
        double BpointTiValue = TypeInference.toDouble(survivalFunction.get2d(BpointTi.toString(), "Sti"));
        double median=BpointTi-(BpointTiValue-0.5)*(BpointTi-ApointTi)/(BpointTiValue-ApointTiValue);

        return median;
    }