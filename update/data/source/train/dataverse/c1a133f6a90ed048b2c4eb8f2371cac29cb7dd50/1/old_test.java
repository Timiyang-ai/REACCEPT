@Test
    public void testSanitizeBasicHTML() {
        System.out.println("sanitizeBasicHTML");
        
        /*String safeStr = "<img src=\"some/png.png\" alt=\"bee\" class=\"some-class\">";
        String sanitized = MarkupChecker.sanitizeBasicHTML(safeStr);
        this.msgu("safeStr: " + safeStr + "\nsanitized: " + sanitized);
        assertTrue(safeStr.equals(sanitized));
        */
        String safeStr = "<script>alert('hi')</script>";
        String sanitized = MarkupChecker.sanitizeBasicHTML(safeStr);
        this.msgu("safeStr: " + safeStr + "\nsanitized: " + sanitized);
        assertTrue(sanitized.equals(""));

        String unsafeStr = "<map name=\"rtdcCO\">";
        safeStr = "<map name=\"rtdcCO\"></map>";
        sanitized = MarkupChecker.sanitizeBasicHTML(safeStr);
        this.msgu("safeStr: " + safeStr + "\nsanitized: " + sanitized);
        assertTrue(safeStr.equals(sanitized));

        unsafeStr = "<area shape=\"rect\" coords=\"42,437,105,450\" href=\"/dvn/dv/rtdc/faces/study/StudyPage.xhtml?globalId=hdl:10904/10006\" title=\"Galactic Center (DHT02)\" alt=\"Galactic Center (DHT02)\">";
        safeStr = unsafeStr;//"<map name=\"rtdcCO\"></map>";
        sanitized = MarkupChecker.sanitizeBasicHTML(safeStr);
        this.msgu("safeStr: " + safeStr + "\nsanitized: " + sanitized);
        assertTrue(safeStr.equals(sanitized));

        
        unsafeStr = "<map name=\"rtdcCO\"><area shape=\"rect\" coords=\"42,437,105,450\" href=\"/dvn/dv/rtdc/faces/study/StudyPage.xhtml?globalId=hdl:10904/10006\" title=\"Galactic Center (DHT02)\" alt=\"Galactic Center (DHT02)\"></map>";
        safeStr = unsafeStr;//"<map name=\"rtdcCO\"></map>";
        sanitized = MarkupChecker.sanitizeBasicHTML(safeStr);
        this.msgu("safeStr: " + safeStr + "\nsanitized: " + sanitized);
        assertTrue(safeStr.equals(sanitized));

        unsafeStr = "<p>hello</";
        safeStr = "hello";//"<map name=\"rtdcCO\"></map>";
        sanitized = MarkupChecker.sanitizeBasicHTML(safeStr);
        this.msgu("safeStr: " + safeStr + "\nsanitized: " + sanitized);
        assertTrue(safeStr.equals(sanitized));

        unsafeStr = "<h1>hello</h2>";
        safeStr = "hello";//"<map name=\"rtdcCO\"></map>";
        sanitized = MarkupChecker.sanitizeBasicHTML(safeStr);
        this.msgu("safeStr: " + safeStr + "\nsanitized: " + sanitized);
        assertTrue(safeStr.equals(sanitized));

    }