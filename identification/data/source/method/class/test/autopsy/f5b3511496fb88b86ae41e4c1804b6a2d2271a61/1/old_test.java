@Test
    public void testIsValidCCN() {
        System.out.println("isValidCCN");
        CreditCardValidator validator = new CreditCardValidator();
        //too short
        assertEquals(false, validator.isValidCCN("123456789031"));
        assertEquals(false, validator.isValidCCN("623 974794 78395 186"));
        assertEquals(false, validator.isValidCCN("12 5678-90-31834"));

        //no cards have 17 digits
        assertEquals(false, validator.isValidCCN("12345678903183426"));
        //too long
        assertEquals(false, validator.isValidCCN("12342387t8373092570375025678902"));
       
        //rules for seperators and grouping for 16 digits
        assertEquals(true, validator.isValidCCN("1234-5678-9031-8342"));// dashes
        assertEquals(true, validator.isValidCCN("1234 5678 9031 8342"));// or spaces

        assertEquals(false, validator.isValidCCN("1234-5678-9031 8342")); //only one seperator
        assertEquals(false, validator.isValidCCN("1234-5678-90-318342")); //only four groups of four
        assertEquals(false, validator.isValidCCN("1234 5678 90 318342")); //only four groups of four
        assertEquals(false, validator.isValidCCN("1-2-3-4-5-6-7-8-9-0-3-1-8-3-4-2")); //only four groups of four

        //amex are fifteen digits, and typically grouped 4 6 5
        //amex cards that strart with 34
        assertEquals(true, validator.isValidCCN("3431 136294 58529"));
        assertEquals(true, validator.isValidCCN("3431-136294-58529"));
        assertEquals(true, validator.isValidCCN("343113629458529"));
        assertEquals(false, validator.isValidCCN("3431 13629458 529"));

        //amex cards that start with 37
        assertEquals(true, validator.isValidCCN("377585291285489"));
        assertEquals(true, validator.isValidCCN("3775-852912-85489"));
        assertEquals(true, validator.isValidCCN("3775 852912 85489"));
        assertEquals(false, validator.isValidCCN("3775-852912 85489"));
        assertEquals(false, validator.isValidCCN("37-7585-29-1285489"));
        assertEquals(false, validator.isValidCCN("377585 29128548 9"));

        //UATP are also 15 digits, start with 1 and are typically 4-5-6
        assertEquals(true, validator.isValidCCN("1409 56201 545229"));
        assertEquals(true, validator.isValidCCN("1409-56201-545229"));
        assertEquals(true, validator.isValidCCN("140956201545229"));
        assertEquals(false, validator.isValidCCN("140 9562015 45229"));
        assertEquals(false, validator.isValidCCN("1409-56201 545229"));

        //nineteen digit (visa) card
        assertEquals(true, validator.isValidCCN("4539747947839518654"));
        assertEquals(true, validator.isValidCCN("4539-7479-4783-9518-654"));
        assertEquals(true, validator.isValidCCN("4539 7479 4783 9518 654"));
        assertEquals(false, validator.isValidCCN("4539-7479 4783 9518 654"));
        assertEquals(false, validator.isValidCCN("45374 79 4783 9518 654"));

        //nineteen digit China UnionPay is 19 digits 6-13 (or 4-4-4-4) beging 62
        assertEquals(true, validator.isValidCCN("6239747947839518659"));
        assertEquals(true, validator.isValidCCN("623974 7947839518659"));
        assertEquals(true, validator.isValidCCN("623974-7947839518659"));
        assertEquals(false, validator.isValidCCN("623974-79478395 18659"));
        assertEquals(false, validator.isValidCCN("62397-47947839518659"));

        assertEquals(true, validator.isValidCCN("5140 2100 1014 4744"));
    }