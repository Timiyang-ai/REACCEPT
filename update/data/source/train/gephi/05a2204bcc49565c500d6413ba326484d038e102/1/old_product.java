public static BigDecimal average(Number[] numbers) {
        if(numbers.length==0){
            return null;
        }
        BigDecimal sum = new BigDecimal(0);
        
        int numbersCount = 0;
        for (Number number : numbers) {
            if (number != null) {
                sum=sum.add(new BigDecimal(number.toString()));
                ++numbersCount;
            }
        }

        return sum.divide(new BigDecimal(numbersCount),RoundingMode.HALF_EVEN);
    }