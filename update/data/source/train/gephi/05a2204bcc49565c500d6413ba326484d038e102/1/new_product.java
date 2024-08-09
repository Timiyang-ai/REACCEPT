public static BigDecimal average(Number[] numbers) {
        if(numbers==null||numbers.length==0){
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

        BigDecimal result;
        try{
            result=sum.divide(new BigDecimal(numbersCount));
        }catch(ArithmeticException ex){
            result=sum.divide(new BigDecimal(numbersCount),10,RoundingMode.HALF_EVEN);//Maximum of 10 decimal digits to avoid periodic number exception.
        }
        return result;
    }