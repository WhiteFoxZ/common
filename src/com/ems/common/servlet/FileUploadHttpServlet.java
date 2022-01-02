//
// Decompiled by Procyon v0.5.36
//

package com.ems.common.servlet;

import com.ems.common.dbcp.DBManager;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.sql.Connection;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import javax.servlet.ServletContext;
import javax.servlet.ServletConfig;
import java.io.File;
import org.apache.log4j.Logger;
import javax.servlet.http.HttpServlet;

public class FileUploadHttpServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;
    private Logger log;
    private File fileUploadPath;

    public FileUploadHttpServlet() {
        this.log = Logger.getLogger((Class)this.getClass());
    }

    public void init(final ServletConfig config) throws ServletException {
        super.init(config);
        final ServletContext context = config.getServletContext();
        final String path = context.getInitParameter("upload_path");
        this.fileUploadPath = new File(path);


        this.log.debug(" fileUploadPath : "+fileUploadPath);


        if (!this.fileUploadPath.exists()) {
            this.fileUploadPath.mkdirs();
            this.log.debug((Object)" fileUploadPath.mkdirs() ");
        }
    }

    public void updateFileDb(final HttpSession session, final Connection con, final String PK_AUTO_KEY, final String TABLE_NAME, final String LOGINID) throws SQLException, IOException {
        PreparedStatement ps = null;
        BufferedInputStream bis = null;
        final String sid = session.getId();
        final File sidDir = new File(String.valueOf(this.fileUploadPath.getAbsolutePath()) + "/" + sid);
        this.log.debug((Object)("sidDir " + sidDir.toString()));
        this.log.debug((Object)("sidDir.exists " + sidDir.exists()));
        String pgname = this.getClass().getName();
        if (pgname.length() > 22) {
            pgname = pgname.substring(0, 22);
        }
        if (sidDir.exists()) {
            ps = con.prepareStatement("insert into all_img_info(FILE_NAME,CONTENT_TYPE,FILE_SIZE,TABLE_NAME,TABLE_KEY,CREATED_OBJECT_ID,CREATED_PROGRAM_ID,FILE) VALUES (?,?,?,?,?,?,?,?) ");
            final File[] list = sidDir.listFiles();
            for (int i = 0; i < list.length; ++i) {
                this.log.debug((Object)("FILE_NAME: " + list[i].getName()));
                this.log.debug((Object)("TABLE_NAME:" + TABLE_NAME));
                this.log.debug((Object)("TABLE_KEY:" + PK_AUTO_KEY));
                this.log.debug((Object)("CREATED_OBJECT_ID:" + LOGINID));
                this.log.debug((Object)("CREATED_PROGRAM_ID:" + pgname));
                bis = new BufferedInputStream(new FileInputStream(list[i]));
                int j = 1;
                ps.setString(j++, list[i].getName());
                ps.setString(j++, UploadUtil.getMimeType(list[i]));
                ps.setLong(j++, list[i].length());
                ps.setString(j++, TABLE_NAME);
                ps.setString(j++, PK_AUTO_KEY);
                ps.setString(j++, LOGINID);
                ps.setString(j++, pgname);
                ps.setBinaryStream(j++, bis, bis.available());
                ps.executeUpdate();
                bis.close();
            }
            deleteDirectory(sidDir);
        }
    }

    public void deleteFileDb(final DBManager dbm, final Connection con, final String TABLE_NAME, final String TABLE_KEY) throws Exception {
        dbm.delete(con, "DELETE FROM all_img_info WHERE TABLE_NAME=? and TABLE_KEY=? ", new String[] { TABLE_NAME, TABLE_KEY });
    }

    public static boolean deleteDirectory(final File path) {
        if (!path.exists()) {
            return false;
        }
        final File[] files = path.listFiles();
        File[] array;
        for (int length = (array = files).length, i = 0; i < length; ++i) {
            final File file = array[i];
            if (file.isDirectory()) {
                deleteDirectory(file);
            }
            else {
                file.delete();
            }
        }
        return path.delete();
    }
}
