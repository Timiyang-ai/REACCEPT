public static String decodePath(String path)
    {
        if (path==null)
            return null;
        // Array to hold all converted characters
        char[] chars=null;
        int n=0;
        // Array to hold a sequence of %encodings
        byte[] bytes=null;
        int b=0;
        
        int len=path.length();
        
        for (int i=0;i<len;i++)
        {
            char c = path.charAt(i);

            if (c=='%' && (i+2)<len)
            {
                if (chars==null)
                {
                    chars=new char[len];
                    bytes=new byte[len];
                    path.getChars(0,i,chars,0);
                }
                bytes[b++]=(byte)(0xff&TypeUtil.parseInt(path,i+1,2,16));
                i+=2;
                continue;
            }
            else if (c==';')
            {
                if (chars==null)
                {
                    chars=new char[len];
                    path.getChars(0,i,chars,0);
                    n=i;
                }
                break;
            }
            else if (bytes==null)
            {
                n++;
                continue;
            }
            
            // Do we have some bytes to convert?
            if (b>0)
            {
                String s=new String(bytes,0,b,__CHARSET);
                s.getChars(0,s.length(),chars,n);
                n+=s.length();
                b=0;
            }
            
            chars[n++]=c;
        }

        if (chars==null)
            return path;

        // if we have a remaining sequence of bytes
        if (b>0)
        {
            String s=new String(bytes,0,b,__CHARSET);
            s.getChars(0,s.length(),chars,n);
            n+=s.length();
        }
        
        return new String(chars,0,n);
    }