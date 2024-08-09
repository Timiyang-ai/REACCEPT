public static synchronized void initialize ()
    {
        if ( !initialized )
        {
            // Class aliases
            XmlUtils.processAnnotations ( Dictionary.class );
            XmlUtils.processAnnotations ( TranslationInformation.class );
            XmlUtils.processAnnotations ( Record.class );
            XmlUtils.processAnnotations ( Value.class );
            XmlUtils.processAnnotations ( Text.class );

            // Initializing global dictionary
            dictionaries = new Dictionary ( "", "Global dictionary" );

            // Marking initialized
            initialized = true;

            // Adding core module dictionary
            addDictionary ( new Dictionary ( new ClassResource ( LanguageManager.class, "resources/core-language.xml" ) ) );
        }
    }