public String sendPostData(URLConnection connection, HTTPSamplerBase sampler) throws IOException {
        // Buffer to hold the post body, except file content
        StringBuilder postedBody = new StringBuilder(1000);

        HTTPFileArg[] files = sampler.getHTTPFiles();

        String contentEncoding = sampler.getContentEncoding();
        if(contentEncoding == null || contentEncoding.length() == 0) {
            contentEncoding = ENCODING;
        }

        // Check if we should do a multipart/form-data or an
        // application/x-www-form-urlencoded post request
        if(sampler.getUseMultipartForPost()) {
            OutputStream out = connection.getOutputStream();

            // Write the form data post body, which we have constructed
            // in the setHeaders. This contains the multipart start divider
            // and any form data, i.e. arguments
            out.write(formDataPostBody);
            // Retrieve the formatted data using the same encoding used to create it
            postedBody.append(new String(formDataPostBody, contentEncoding));

            // Add any files
            for (int i=0; i < files.length; i++) {
                HTTPFileArg file = files[i];
                // First write the start multipart file
                byte[] header = file.getHeader().getBytes();  // TODO - charset?
                out.write(header);
                // Retrieve the formatted data using the same encoding used to create it
                postedBody.append(new String(header)); // TODO - charset?
                // Write the actual file content
                writeFileToStream(file.getPath(), out);
                // We just add placeholder text for file content
                postedBody.append("<actual file content, not shown here>"); // $NON-NLS-1$
                // Write the end of multipart file
                byte[] fileMultipartEndDivider = getFileMultipartEndDivider();
                out.write(fileMultipartEndDivider);
                // Retrieve the formatted data using the same encoding used to create it
                postedBody.append(new String(fileMultipartEndDivider, ENCODING));
                if(i + 1 < files.length) {
                    out.write(CRLF);
                    postedBody.append(new String(CRLF, SampleResult.DEFAULT_HTTP_ENCODING));
                }
            }
            // Write end of multipart
            byte[] multipartEndDivider = getMultipartEndDivider();
            out.write(multipartEndDivider);
            postedBody.append(new String(multipartEndDivider, ENCODING));

            out.flush();
            out.close();
        }
        else {
            // If there are no arguments, we can send a file as the body of the request
            if(sampler.getArguments() != null && !sampler.hasArguments() && sampler.getSendFileAsPostBody()) {
                OutputStream out = connection.getOutputStream();
                // we're sure that there is at least one file because of
                // getSendFileAsPostBody method's return value.
                HTTPFileArg file = files[0];
                writeFileToStream(file.getPath(), out);
                out.flush();
                out.close();

                // We just add placeholder text for file content
                postedBody.append("<actual file content, not shown here>"); // $NON-NLS-1$
            }
            else if (formDataUrlEncoded != null){ // may be null for PUT
                // In an application/x-www-form-urlencoded request, we only support
                // parameters, no file upload is allowed
                OutputStream out = connection.getOutputStream();
                out.write(formDataUrlEncoded);
                out.flush();
                out.close();

                postedBody.append(new String(formDataUrlEncoded, contentEncoding));
            }
        }
        return postedBody.toString();
    }