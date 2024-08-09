public static synchronized void initialize ()
    {
        if ( !initialized )
        {
            initialized = true;

            // Initial language
            language = supportedLanguages.contains ( DEFAULT ) ? DEFAULT : ENGLISH;
            updateLocale ();

            // Default data
            globalDictionary = new Dictionary ();

            // Class aliases
            XmlUtils.processAnnotations ( Dictionary.class );
            XmlUtils.processAnnotations ( LanguageInfo.class );
            XmlUtils.processAnnotations ( Record.class );
            XmlUtils.processAnnotations ( Value.class );
            XmlUtils.processAnnotations ( Text.class );
            XmlUtils.processAnnotations ( Tooltip.class );
            XmlUtils.processAnnotations ( TooltipType.class );
            XmlUtils.processAnnotations ( TooltipWay.class );

            // Basic language updaters
            registerLanguageUpdater ( new JLabelLU () );
            registerLanguageUpdater ( new AbstractButtonLU () );
            registerLanguageUpdater ( new JTextComponentLU () );
            registerLanguageUpdater ( new JTabbedPaneLU () );
            registerLanguageUpdater ( new JProgressBarLU () );
            registerLanguageUpdater ( new JFileChooserLU () );
            registerLanguageUpdater ( new FrameLU () );
            registerLanguageUpdater ( new DialogLU () );
            registerLanguageUpdater ( new JInternalFrameLU () );

            // Tooltip support
            setTooltipLanguageSupport ( new SwingTooltipLanguageSupport () );

            // Language listener for components update
            addLanguageListener ( new LanguageListener ()
            {
                @Override
                public void languageChanged ( final String oldLang, final String newLang )
                {
                    updateAll ();
                }

                @Override
                public void dictionaryAdded ( final Dictionary dictionary )
                {
                    updateSmart ( dictionary );
                }

                @Override
                public void dictionaryRemoved ( final Dictionary dictionary )
                {
                    updateSmart ( dictionary );
                }

                @Override
                public void dictionariesCleared ()
                {
                    updateAll ();
                }

                private void updateAll ()
                {
                    // Notifying registered key listeners
                    if ( languageKeyListeners.size () > 0 )
                    {
                        fireAllLanguageKeysUpdated ();
                    }

                    // Updating registered components
                    updateComponents ();
                }

                private void updateSmart ( final Dictionary dictionary )
                {
                    // Gathering all changed keys
                    final List<String> relevantKeys = LanguageUtils.gatherKeys ( dictionary );

                    // Notifying registered key listeners
                    if ( languageKeyListeners.size () > 0 )
                    {
                        for ( final String key : relevantKeys )
                        {
                            fireLanguageKeyUpdated ( key );
                        }
                    }

                    // Updating relevant components
                    updateComponents ( relevantKeys );
                }
            } );

            // Default WebLaF dictionary
            loadDefaultDictionary ();
        }
    }