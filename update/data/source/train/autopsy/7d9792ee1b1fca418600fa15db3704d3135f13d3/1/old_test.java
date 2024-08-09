@Test
    public void testIsValidCCN() {
        System.out.println("isValidCCN");
        CreditCardValidator validator = new CreditCardValidator();
        //basic luhn check
        assertEquals(true, validator.isValidCCN("123456789031"));
        assertEquals(false, validator.isValidCCN("12345678902"));

        //rules for seperators and grouping for 16 digits
        assertEquals(true, validator.isValidCCN("1234-5678-9031-8342"));// dashes
        assertEquals(true, validator.isValidCCN("1234 5678 9031 8342"));// or spaces

        assertEquals(false, validator.isValidCCN("1234-5678-9031 8342")); //only one seperator
        assertEquals(false, validator.isValidCCN("1234-5678-90-318342")); //only four groups of four
        assertEquals(false, validator.isValidCCN("1234 5678 90 318342")); //only four groups of four
        assertEquals(false, validator.isValidCCN("1-2-3-4-5-6-7-8-9-0-3-1-8-3-4-2")); //only four groups of four

        //non 16 digits have no rules on grouping
        assertEquals(true, validator.isValidCCN("1234-5678-90-31834"));
        assertEquals(false, validator.isValidCCN("12 5678-90-31834")); //still can have only one seperator

        //amex are fifteen digits, and typically grouped 4 6 5
        //amex cards that strart with 34
        assertEquals(true, validator.isValidCCN("3431 136294 58529")); 
        assertEquals(true, validator.isValidCCN("3431-136294-58529")); 
        assertEquals(true, validator.isValidCCN("343113629458529")); 

        assertEquals(false, validator.isValidCCN("3431-136294 58529"));
        assertEquals(false, validator.isValidCCN("34 31 136294 58 529"));
        
        //amex cards that start with 37
        assertEquals(true, validator.isValidCCN("377585291285489")); 
        assertEquals(true, validator.isValidCCN("3775-852912-85489")); 
        assertEquals(true, validator.isValidCCN("3775 852912 85489")); 
        assertEquals(false, validator.isValidCCN("3775-852912 85489")); 
        assertEquals(false, validator.isValidCCN("37-7585-29-1285489")); 
        assertEquals(false, validator.isValidCCN("377 5 8 5 29128548 9")); 
    }