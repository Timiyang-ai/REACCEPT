@Test
    public void testSendPostData_FileAsBody() throws IOException {
        sampler.setMethod(HTTPConstants.POST);
        setupFilepart(sampler, "", temporaryFile, "");
        
        // Check using default encoding
        postWriter.setHeaders(connection, sampler);
        postWriter.sendPostData(connection, sampler);

        checkContentLength(connection, TEST_FILE_CONTENT.length);        
        checkArraysHaveSameContent(TEST_FILE_CONTENT, connection.getOutputStreamContent());
        connection.disconnect();
        
        // Check using a different encoding
        
        String otherEncoding;
        final String fileEncoding = System.getProperty( "file.encoding");// $NON-NLS-1$
        log.info("file.encoding: {}", fileEncoding);
        if (UTF_8.equalsIgnoreCase(fileEncoding) || "UTF8".equalsIgnoreCase(fileEncoding)){// $NON-NLS-1$
            otherEncoding="ISO-8859-1"; // $NON-NLS-1$
        } else {
            otherEncoding=UTF_8;
        }
        log.info("Using other encoding: {}", otherEncoding);
        establishConnection();
        sampler.setContentEncoding(otherEncoding);
        // File content is sent as binary, so the content encoding should not change the file data
        postWriter.setHeaders(connection, sampler);
        postWriter.sendPostData(connection, sampler);
        
        checkContentLength(connection, TEST_FILE_CONTENT.length);        
        checkArraysHaveSameContent(TEST_FILE_CONTENT, connection.getOutputStreamContent());
        // Check that other encoding is not the current encoding
        checkArraysHaveDifferentContent(new String(TEST_FILE_CONTENT) // TODO - charset?
            .getBytes(otherEncoding), connection.getOutputStreamContent());
        
        // If we have both file as body, and form data, then only form data will be sent
        setupFormData(sampler);
        establishConnection();
        sampler.setContentEncoding("");
        postWriter.setHeaders(connection, sampler);
        postWriter.sendPostData(connection, sampler);
        
        checkContentTypeUrlEncoded(connection);
        byte[] expectedUrl = "title=mytitle&description=mydescription".getBytes(); // TODO - charset?
        checkContentLength(connection, expectedUrl.length);
        checkArraysHaveSameContent(expectedUrl, connection.getOutputStreamContent());
    }