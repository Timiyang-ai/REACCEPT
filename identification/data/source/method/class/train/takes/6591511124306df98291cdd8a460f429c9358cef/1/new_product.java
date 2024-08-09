private Opt<String> urn(final String user, final String pwd) {
            final String urn;
            try {
                urn = this.usernames.get(
                    String.format(
                        PsBasic.Default.KEY_FORMAT,
                        URLEncoder.encode(
                            user,
                            PsBasic.Default.ENCODING
                        ),
                        URLEncoder.encode(
                            pwd,
                            PsBasic.Default.ENCODING
                        )
                    )
                );
            } catch (final UnsupportedEncodingException ex) {
                throw new IllegalStateException(ex);
            }
            final Opt<String> opt;
            if (urn == null) {
                opt = new Opt.Empty<>();
            } else {
                opt = new Opt.Single<>(urn);
            }
            return opt;
        }