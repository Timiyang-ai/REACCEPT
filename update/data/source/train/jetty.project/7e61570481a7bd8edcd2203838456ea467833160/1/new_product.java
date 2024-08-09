public static String canonicalPath(String path)
    {
        if (path == null || path.isEmpty())
            return path;

        boolean slash = true;
        int end = path.length();
        int i = 0;
        
        loop:
        while (i<end)
        {
            char c = path.charAt(i);
            switch(c)
            {
                case '/':
                    slash = true;
                    break;
                    
                case '.':
                    if (slash)
                        break loop;
                    slash = false;
                    break;
                    
                case '?':
                    return path;
                    
                default:
                    slash = false;
            }
        
            i++;
        }
        
        if(i==end)
            return path;
        
        StringBuilder canonical = new StringBuilder(path.length());
        canonical.append(path,0,i);
        
        int dots = 1;
        i++;
        while (i<=end)
        {
            char c = i<end?path.charAt(i):'\0';
            switch(c)
            {
                case '\0':
                case '/':
                case '?':
                    switch(dots)
                    {
                        case 0:
                            if (c!='\0')
                                canonical.append(c);
                            break;
                            
                        case 1:
                            if (c=='?')
                                canonical.append(c);
                            break;
                            
                        case 2:
                            if (canonical.length()<2)
                                return null;
                            canonical.setLength(canonical.length()-1);
                            canonical.setLength(canonical.lastIndexOf("/")+1);
                            if (c=='?')
                                canonical.append(c);
                            break;
                        default:
                            while (dots-->0)
                                canonical.append('.');
                            if (c!='\0')
                                canonical.append(c);
                    }
                    
                    slash = true;
                    dots = 0;
                    break;
                    
                case '.':
                    if (dots>0)
                        dots++;
                    else if (slash)
                        dots = 1;
                    slash = false;
                    break;
                    
                default:
                    while (dots-->0)
                        canonical.append('.');
                    canonical.append(c);
                    dots = 0;
                    slash = false;
            }
        
            i++;
        }
        return canonical.toString();
    }