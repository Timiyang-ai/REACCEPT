private Opt<String> urn(final String user, final String pwd) {
            final String urn;
            try {
                urn = this.usernames.get(
                    String.format(
                        Default.KEY_FORMAT,
                        URLEncoder.encode(
                            user,
                            Default.ENCODING
                        ),
                        URLEncoder.encode(
                            pwd,
                            Default.ENCODING
                        )
                    )
                );
            } catch (final UnsupportedEncodingException ex) {
                throw new IllegalStateException(ex);
            }
            final Opt<String> opt;
            if (urn == null) {
                opt = new Opt.Empty<String>();
            } else {
                opt = new Opt.Single<String>(urn);
            }
            return opt;
        }