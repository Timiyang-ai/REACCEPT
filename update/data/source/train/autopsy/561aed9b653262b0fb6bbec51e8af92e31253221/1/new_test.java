@Test
    public void testIsValidCCN16() {
        System.out.println("isValidCCN");

        //rules for separators and grouping for 16 digits
        assertEquals(true,  CreditCardValidator.isValidCCN("1234567890318342"));// dashes
        assertEquals(true,  CreditCardValidator.isValidCCN("1234-5678-9031-8342"));// dashes
        assertEquals(true,  CreditCardValidator.isValidCCN("1234 5678 9031 8342"));// or spaces
        
        assertEquals(false,  CreditCardValidator.isValidCCN("1234567890318341"));// luhn
        assertEquals(false,  CreditCardValidator.isValidCCN("1234-5678-9031 8342")); //only one seperator
        assertEquals(false,  CreditCardValidator.isValidCCN("1234-5678-90-318342")); //only four groups of four
        assertEquals(false,  CreditCardValidator.isValidCCN("1234 5678 90 318342")); //only four groups of four
        assertEquals(false,  CreditCardValidator.isValidCCN("1-2-3-4-5-6-7-8-9-0-3-1-8-3-4-2")); //only four groups of four
    }