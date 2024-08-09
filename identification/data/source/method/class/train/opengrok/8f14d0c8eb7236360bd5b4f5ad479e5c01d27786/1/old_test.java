    @Test
    public void parseAnnotation() throws Exception {
        String revId1 = "cd283405560689372626a69d5331c467bce71656";
        String revId2 = "30ae764b12039348766291100308556675ca11ab";
        String revId3 = "2394823984cde2390345435a9237bd7c25932342";
        String author1 = "Author Name";
        String author2 = "Author With Long Name";
        String author3 = "Author Named Jr.";
        String output = revId1 + " file1.ext   (" + author1 + "     2005-06-06 16:38:26 -0400 272) \n" +
                revId2 + " file2.h (" + author2 + "     2007-09-10 23:02:45 -0400 273)   if (some code)\n" +
                revId3 + " file2.c  (" + author3 + "      2006-09-20 21:47:42 -0700 274)           call_function(i);\n";
        String fileName = "something.ext";
        
        GitAnnotationParser parser = new GitAnnotationParser(fileName);
        parser.processStream(new ByteArrayInputStream(output.getBytes()));
        Annotation result = parser.getAnnotation();
        
        assertNotNull(result);
        assertEquals(3, result.size());
        for (int i = 1; i <= 3; i++) {
            assertEquals(true, result.isEnabled(i));
        }
        assertEquals(revId1, result.getRevision(1));
        assertEquals(revId2, result.getRevision(2));
        assertEquals(revId3, result.getRevision(3));
        assertEquals(author1, result.getAuthor(1));
        assertEquals(author2, result.getAuthor(2));
        assertEquals(author3, result.getAuthor(3));
        assertEquals(author2.length(), result.getWidestAuthor());
        assertEquals(revId1.length(), result.getWidestRevision());
        assertEquals(fileName, result.getFilename());
    }