public T remove(T defaultValue){
        final T[] values = this.values;
        int popIndex = this.popIndex;

        if(popIndex == pushIndex){
            //Underflow
            return defaultValue;
        }
        T result = values[popIndex];
        values[popIndex] = null;

        popIndex++;
        if(popIndex == values.length){
            popIndex = 0;
        }
        this.popIndex = popIndex;

        return result;
    }