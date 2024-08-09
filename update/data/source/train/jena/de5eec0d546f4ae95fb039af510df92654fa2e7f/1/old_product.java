@Override
                public void read(InputStream in, String baseURI, ContentType ct, RDFParserOutput output, Context context)
                {
                    Lang lang = RDFLanguages.convert(language) ;
                    LangRIOT parser = RiotReader.createParserQuads(in, lang, baseURI, output) ;
                    parser.parse() ;
                }