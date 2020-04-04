// 
// Decompiled by Procyon v0.5.36
// 

package com.ems.common.servlet;

import javax.activation.MimetypesFileTypeMap;
import java.io.File;
import org.apache.log4j.Logger;

public class UploadUtil
{
    private static Logger log;
    
    static {
        UploadUtil.log = Logger.getLogger("com.ems.common.servlet.UploadUtil");
    }
    
    public static String getMimeType(final File file) {
        String mimetype = "";
        if (file.exists()) {
            final String fileName = file.getName();
            final String suffix = getSuffix(fileName);
            if (suffix.equalsIgnoreCase("png")) {
                mimetype = "image/png";
            }
            else if (suffix.equalsIgnoreCase("jpg") || suffix.equalsIgnoreCase("jpeg")) {
                mimetype = "image/jpeg";
            }
            else if (suffix.equalsIgnoreCase("gif") || suffix.equalsIgnoreCase("gif")) {
                mimetype = "image/gif";
            }
            else {
                final MimetypesFileTypeMap mtMap = new MimetypesFileTypeMap();
                mimetype = mtMap.getContentType(file);
            }
        }
        return mimetype;
    }
    
    private static String getSuffix(final String filename) {
        String suffix = "";
        final int pos = filename.lastIndexOf(46);
        if (pos > 0 && pos < filename.length() - 1) {
            suffix = filename.substring(pos + 1);
        }
        return suffix;
    }
}
