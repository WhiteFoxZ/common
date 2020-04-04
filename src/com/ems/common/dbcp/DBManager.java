// 
// Decompiled by Procyon v0.5.36
// 

package com.ems.common.dbcp;

import java.io.InputStream;
import java.io.FileNotFoundException;
import java.io.Writer;
import java.util.Enumeration;
import java.io.CharArrayReader;
import oracle.sql.CLOB;
import java.util.Hashtable;
import java.io.Reader;
import java.io.StringReader;
import java.util.Collection;
import java.util.Set;
import java.util.HashMap;
import com.ems.common.util.EmsHashtable;
import java.util.Vector;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.ArrayList;
import org.apache.log4j.Logger;

public class DBManager
{
    private Logger log;
    private ArrayList<EmsMetaData> metaHash;
    private DataSource ds;
    
    public DBManager(final DataSource ds) {
        this.log = Logger.getLogger((Class)this.getClass());
        this.metaHash = null;
        this.ds = null;
        this.ds = ds;
    }
    
    public Connection getConnection() throws SQLException {
        this.log.debug((Object)("getConnection \uc804" + this.ds));
        final Connection con = this.ds.getConnection();
        con.setAutoCommit(false);
        this.log.debug((Object)("getConnection \ud6c4" + this.ds));
        this.log.debug((Object)("con \uc815\ubcf4 ===> " + con));
        return con;
    }
    
    public long selectNextSeq(final String seqName) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        long nextSeq = 0L;
        final String query = "select " + seqName + ".nextval from dual";
        try {
            con = this.ds.getConnection();
            ps = con.prepareStatement(query, 1004, 1007);
            rs = ps.executeQuery();
            while (rs.next()) {
                nextSeq = rs.getLong(1);
            }
        }
        catch (Exception e) {
            final StringBuffer buf = new StringBuffer();
            buf.append(this.toString()).append(" | ");
            buf.append(e.toString()).append(" | ");
            buf.append(" Query : ").append(query);
            this.log.error((Object)buf.toString());
            return nextSeq;
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                }
                catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                }
                catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            if (con != null) {
                this.connClose(con);
            }
        }
        if (rs != null) {
            try {
                rs.close();
            }
            catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        if (ps != null) {
            try {
                ps.close();
            }
            catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        if (con != null) {
            this.connClose(con);
        }
        return nextSeq;
    }
    
    public long selectNextSeqMysql(final Connection con, final String tabname, final String colName) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        long nextSeq = 0L;
        final String query = "select last_insert_id(" + colName + ") from " + tabname + " order by " + colName + " desc limit 1";
        try {
            ps = con.prepareStatement(query, 1004, 1007);
            rs = ps.executeQuery();
            this.log.debug((Object)query);
            while (rs.next()) {
                nextSeq = rs.getLong(1);
            }
        }
        catch (Exception e) {
            final StringBuffer buf = new StringBuffer();
            buf.append(this.toString()).append(" | ");
            buf.append(e.toString()).append(" | ");
            buf.append(" Query : ").append(query);
            this.log.error((Object)buf.toString());
            if (rs != null) {
                try {
                    rs.close();
                }
                catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                }
                catch (Exception e2) {
                    e2.printStackTrace();
                }
                return nextSeq;
            }
            return nextSeq;
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                }
                catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                }
                catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
        if (rs != null) {
            try {
                rs.close();
            }
            catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        if (ps != null) {
            try {
                ps.close();
            }
            catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return nextSeq;
    }
    
    public long selectNextSeq(final Connection con, final String seqName) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        long nextSeq = 0L;
        final String query = "select " + seqName + ".nextval from dual";
        try {
            ps = con.prepareStatement(query, 1004, 1007);
            rs = ps.executeQuery();
            while (rs.next()) {
                nextSeq = rs.getLong(1);
            }
        }
        catch (Exception e) {
            final StringBuffer buf = new StringBuffer();
            buf.append(this.toString()).append(" | ");
            buf.append(e.toString()).append(" | ");
            buf.append(" Query : ").append(query);
            this.log.error((Object)buf.toString());
            if (rs != null) {
                try {
                    rs.close();
                }
                catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                }
                catch (Exception e2) {
                    e2.printStackTrace();
                }
                return nextSeq;
            }
            return nextSeq;
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                }
                catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                }
                catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
        if (rs != null) {
            try {
                rs.close();
            }
            catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        if (ps != null) {
            try {
                ps.close();
            }
            catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return nextSeq;
    }
    
    public int selectRecordCount(final String query, final String[] prString) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;
        try {
            con = this.ds.getConnection();
            ps = con.prepareStatement(query, 1004, 1007);
            this.log.debug((Object)query);
            for (int i = 0; i < prString.length; ++i) {
                ps.setString(i + 1, prString[i]);
                this.log.debug((Object)(String.valueOf(i) + ":[" + prString[i] + "]"));
            }
            rs = ps.executeQuery();
            while (rs.next()) {
                count = rs.getInt(1);
            }
        }
        catch (Exception e) {
            this.loging(query, prString, e);
            return count;
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                }
                catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                }
                catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            if (con != null) {
                this.connClose(con);
            }
        }
        if (rs != null) {
            try {
                rs.close();
            }
            catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        if (ps != null) {
            try {
                ps.close();
            }
            catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        if (con != null) {
            this.connClose(con);
        }
        return count;
    }
    
    public int selectRecordCount(final String query, final Vector<?> v) {
        String[] prString = new String[v.size()];
        prString = v.toArray(prString);
        return this.selectRecordCount(query, prString);
    }
    
    public int selectRecordCount(final Connection con, final String query, final String[] prString) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;
        try {
            ps = con.prepareStatement(query, 1004, 1007);
            this.log.debug((Object)query);
            for (int i = 0; i < prString.length; ++i) {
                ps.setString(i + 1, prString[i]);
                this.log.debug((Object)("# " + prString[i]));
            }
            rs = ps.executeQuery();
            while (rs.next()) {
                count = rs.getInt(1);
            }
        }
        catch (Exception e) {
            this.loging(query, prString, e);
            if (rs != null) {
                try {
                    rs.close();
                }
                catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                }
                catch (Exception e2) {
                    e2.printStackTrace();
                }
                return count;
            }
            return count;
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                }
                catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                }
                catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
        if (rs != null) {
            try {
                rs.close();
            }
            catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        if (ps != null) {
            try {
                ps.close();
            }
            catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return count;
    }
    
    public int selectRecordCount(final Connection con, final String query, final Vector<?> v) throws SQLException {
        String[] prString = new String[v.size()];
        prString = v.toArray(prString);
        return this.selectRecordCount(con, query, prString);
    }
    
    public EmsHashtable[] selectMultipleRecord(final String query, final String[] prString) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        EmsHashtable[] hashData = null;
        try {
            con = this.ds.getConnection();
            ps = con.prepareStatement(query, 1004, 1007);
            this.log.debug((Object)query);
            if (prString != null) {
                for (int i = 0; i < prString.length; ++i) {
                    ps.setString(i + 1, prString[i]);
                    this.log.debug((Object)(String.valueOf(i) + "[" + prString[i] + "]\n"));
                }
            }
            rs = ps.executeQuery();
            rs.getMetaData();
            final HashResultSet hrs = new HashResultSet(rs);
            hashData = hrs.getHashData();
            this.metaHash = (ArrayList<EmsMetaData>)hrs.getMetaHash();
        }
        catch (Exception e) {
            this.loging(query, prString, e);
            return hashData;
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                }
                catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                }
                catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            if (con != null) {
                this.connClose(con);
            }
        }
        if (rs != null) {
            try {
                rs.close();
            }
            catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        if (ps != null) {
            try {
                ps.close();
            }
            catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        if (con != null) {
            this.connClose(con);
        }
        return hashData;
    }
    
    public EmsHashtable[] selectMultipleRecord(final String query, final Vector<String> v) {
        String[] prString = new String[v.size()];
        prString = v.toArray(prString);
        return this.selectMultipleRecord(query, prString);
    }
    
    public EmsHashtable[] selectMultipleRecord(final String query, final ArrayList<String> v) {
        String[] prString = new String[v.size()];
        prString = v.toArray(prString);
        return this.selectMultipleRecord(query, prString);
    }
    
    public EmsHashtable[] selectMultipleRecord(final Connection con, final String query, final Vector<String> v) {
        String[] prString = new String[v.size()];
        prString = v.toArray(prString);
        return this.selectMultipleRecord(con, query, prString);
    }
    
    public EmsHashtable[] selectMultipleRecord(final Connection con, final String query, final String[] prString) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        EmsHashtable[] hashData = null;
        try {
            ps = con.prepareStatement(query, 1004, 1007);
            this.log.debug((Object)("# " + query));
            if (prString != null) {
                for (int i = 0; i < prString.length; ++i) {
                    ps.setString(i + 1, prString[i]);
                    this.log.debug((Object)("# " + prString[i]));
                }
            }
            rs = ps.executeQuery();
            final HashResultSet hrs = new HashResultSet(rs);
            hashData = hrs.getHashData();
            this.metaHash = (ArrayList<EmsMetaData>)hrs.getMetaHash();
        }
        catch (Exception e) {
            e.printStackTrace();
            this.loging(query, prString, e);
            if (rs != null) {
                try {
                    rs.close();
                }
                catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                }
                catch (Exception e2) {
                    e2.printStackTrace();
                }
                return hashData;
            }
            return hashData;
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                }
                catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                }
                catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
        if (rs != null) {
            try {
                rs.close();
            }
            catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        if (ps != null) {
            try {
                ps.close();
            }
            catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return hashData;
    }
    
    public EmsHashtable[] selectMultipleRecord(final Connection con, final String fileName, final String[] prString, final Object obj) throws Exception {
        final String q1 = this.sql(obj.getClass(), fileName);
        return this.selectMultipleRecord(con, q1, prString);
    }
    
    public EmsHashtable[] selectPagingRecord(final String str, final String order, final String[] prString, final int startNum, final int lastNum) {
        final Vector<String> v1 = new Vector<String>();
        for (int i = 0; i < prString.length; ++i) {
            v1.add(prString[i]);
        }
        v1.add(new StringBuilder().append(startNum).toString());
        v1.add(new StringBuilder().append(lastNum).toString());
        final StringBuffer q1 = new StringBuffer("\n");
        q1.append("select * from ( \n");
        q1.append("select RESULT.*,rownum as rnum  from ( \n");
        q1.append(str);
        if (!order.equals("")) {
            q1.append(order);
        }
        q1.append("\n ) RESULT ) where rnum>=:startNum and ROWNUM< :lastNum \n");
        final EmsHashtable[] hash = this.selectMultipleRecord(q1.toString(), v1);
        return hash;
    }
    
    public EmsHashtable[] selectPagingRecord(final Connection con, final String str, final String order, final String[] prString, final int startNum, final int lastNum) {
        final Vector<String> v1 = new Vector<String>();
        for (int i = 0; i < prString.length; ++i) {
            v1.add(prString[i]);
        }
        v1.add(new StringBuilder().append(startNum).toString());
        v1.add(new StringBuilder().append(lastNum).toString());
        final StringBuffer q1 = new StringBuffer("\n");
        q1.append("select * from ( \n");
        q1.append("select RESULT.*,rownum as rnum  from ( \n");
        q1.append(str);
        if (!order.equals("")) {
            q1.append(order);
        }
        q1.append("\n ) RESULT ) where rnum>=:startNum and ROWNUM< :lastNum \n");
        final EmsHashtable[] hash = this.selectMultipleRecord(con, q1.toString(), v1);
        return hash;
    }
    
    public EmsHashtable[] selectPagingRecord(final String str, final String order, final Vector<?> v, final int startNum, final int lastNum) {
        String[] prString = new String[v.size()];
        prString = v.toArray(prString);
        return this.selectPagingRecord(str, order, prString, startNum, lastNum);
    }
    
    public int selectPagingCount(String str, final String[] prString) {
        str = str.toUpperCase();
        str = str.replaceAll("\\t", " ");
        final StringBuffer q1 = new StringBuffer("\n");
        int totalCount = 0;
        q1.append("select count(*) ");
        final int i = str.indexOf(" FROM ");
        q1.append(str.substring(i));
        totalCount = this.selectRecordCount(q1.toString(), prString);
        return totalCount;
    }
    
    public int selectPagingCount(final Connection con, String str, final String[] prString) throws SQLException {
        str = str.toUpperCase();
        str = str.replaceAll("\\t", " ");
        final StringBuffer q1 = new StringBuffer("\n");
        int totalCount = 0;
        q1.append("select count(*) ");
        final int i = str.indexOf(" FROM ");
        q1.append(str.substring(i));
        totalCount = this.selectRecordCount(con, q1.toString(), prString);
        return totalCount;
    }
    
    public int selectPagingCount(final String str, final Vector<?> v) {
        String[] prString = new String[v.size()];
        prString = v.toArray(prString);
        return this.selectPagingCount(str, prString);
    }
    
    public int selectPagingCount(final Connection con, final String str, final Vector<?> v) throws SQLException {
        String[] prString = new String[v.size()];
        prString = v.toArray(prString);
        return this.selectPagingCount(con, str, prString);
    }
    
    public int insert(final Connection con, final String query, final String[] prString) throws Exception {
        final int tf = this.excute(con, query, prString);
        return tf;
    }
    
    public int update(final Connection con, final String query, final ArrayList<String> al) throws Exception {
        final String[] prString = (String[]) al.toArray(new String[al.size()]);
        final int tf = this.excute(con, query, prString);
        return tf;
    }
    
    public int update(final Connection con, final String query, final String[] prString) throws Exception {
        final int tf = this.excute(con, query, prString);
        return tf;
    }
    
    public int delete(final Connection con, final String query, final String[] prString) throws Exception {
        final int tf = this.excute(con, query, prString);
        return tf;
    }
    
    public int add(final Connection con, final HashMap<?, ?> map, final String tableName) throws Exception {
        if (map instanceof DbHashMap) {
            this.log.debug((Object)" map instanceof DbHashMap ");
            ((DbHashMap)map).insert();
        }
        final StringBuffer q1 = new StringBuffer("insert into " + tableName + "( ");
        final StringBuffer q2 = new StringBuffer(" values ( ");
        final StringBuffer sb = new StringBuffer("");
        final Set<?> key = map.keySet();
        final Collection<?> cl = map.values();
        final String[] arrStr = key.toArray(new String[key.size()]);
        final String[] prString = cl.toArray(new String[cl.size()]);
        for (int i = 0; i < arrStr.length; ++i) {
            q1.append(arrStr[i]).append(", ");
            q2.append("?").append(", ");
        }
        sb.append(String.valueOf(q1.substring(0, q1.lastIndexOf(","))) + " )");
        sb.append(String.valueOf(q2.substring(0, q2.lastIndexOf(","))) + " )");
        return this.insert(con, sb.toString(), prString);
    }
    
    public int addGetKey(final Connection con, final HashMap<?, ?> map, final String tableName) throws Exception {
        if (map instanceof DbHashMap) {
            this.log.debug((Object)" map instanceof DbHashMap ");
            ((DbHashMap)map).insert();
        }
        final StringBuffer q1 = new StringBuffer("insert into " + tableName + "( ");
        final StringBuffer q2 = new StringBuffer(" values ( ");
        final StringBuffer sb = new StringBuffer("");
        final Set<?> key = map.keySet();
        final Collection<?> cl = map.values();
        final String[] arrStr = key.toArray(new String[key.size()]);
        final String[] prString = cl.toArray(new String[cl.size()]);
        for (int i = 0; i < arrStr.length; ++i) {
            q1.append(arrStr[i]).append(", ");
            q2.append("?").append(", ");
        }
        sb.append(String.valueOf(q1.substring(0, q1.lastIndexOf(","))) + " )");
        sb.append(String.valueOf(q2.substring(0, q2.lastIndexOf(","))) + " )");
        return this.excuteGetKey(con, sb.toString(), prString, tableName);
    }
    
    public int modify(final Connection con, final HashMap<?, String> map, final String tableName, final String whereSql, final String[] whereStr) throws Exception {
        if (map instanceof DbHashMap) {
            this.log.debug((Object)" map instanceof DbHashMap ");
            ((DbHashMap)map).update();
        }
        final StringBuffer q1 = new StringBuffer("update " + tableName + " set ");
        final StringBuffer sb = new StringBuffer("");
        final Set<?> key = map.keySet();
        final Collection<String> cl = map.values();
        final String[] arrStr = key.toArray(new String[key.size()]);
        final Vector<String> v = new Vector<String>();
        v.addAll(cl);
        for (int i = 0; i < whereStr.length; ++i) {
            v.add(whereStr[i]);
        }
        final String[] prString = v.toArray(new String[v.size()]);
        for (int j = 0; j < arrStr.length; ++j) {
            q1.append(arrStr[j]).append("=").append("?").append(",");
        }
        sb.append(String.valueOf(q1.substring(0, q1.lastIndexOf(","))) + " where " + whereSql);
        return this.update(con, sb.toString(), prString);
    }
    
    private final int excute(final Connection con, final String query, final String[] prString) throws Exception {
        int tf = -1;
        final StringBuffer pr = new StringBuffer("\n");
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(query);
            for (int i = 0; i < prString.length; ++i) {
                ps.setCharacterStream(i + 1, new StringReader(prString[i]), prString[i].length());
                pr.append(String.valueOf(i) + "[" + prString[i] + "]\n");
            }
            tf = ps.executeUpdate();
        }
        catch (Exception e) {
            throw e;
        }
        finally {
            if (ps != null) {
                try {
                    ps.close();
                }
                catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            this.log.debug((Object)("query:[" + query + "]"));
            this.log.debug((Object)pr.toString());
        }
        if (ps != null) {
            try {
                ps.close();
            }
            catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        this.log.debug((Object)("query:[" + query + "]"));
        this.log.debug((Object)pr.toString());
        return tf;
    }
    
    private final int excuteGetKey(final Connection con, final String query, final String[] prString, final String tname) throws Exception {
        int tf = -1;
        final StringBuffer pr = new StringBuffer("\n");
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement(query);
            for (int i = 0; i < prString.length; ++i) {
                ps.setCharacterStream(i + 1, new StringReader(prString[i]), prString[i].length());
                pr.append(String.valueOf(i) + "[" + prString[i] + "]\n");
            }
            ps.executeUpdate();
            rs = ps.executeQuery("select last_insert_id() AS AUTO_KEY from " + tname);
            if (rs.first()) {
                tf = (int)rs.getDouble(1);
            }
        }
        catch (Exception e) {
            this.loging(query, prString, e);
            throw e;
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                }
                catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                }
                catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            this.log.debug((Object)("query:[" + query + "]"));
            this.log.debug((Object)pr.toString());
        }
        if (rs != null) {
            try {
                rs.close();
            }
            catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        if (ps != null) {
            try {
                ps.close();
            }
            catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        this.log.debug((Object)("query:[" + query + "]"));
        this.log.debug((Object)pr.toString());
        return tf;
    }
    
    public void clobUpdate(final Connection con, final String q2, final String[] prString2, final Hashtable<?, ?> clobHash) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        final Enumeration<?> en = clobHash.keys();
        String column = null;
        try {
            con.setAutoCommit(false);
            ps = con.prepareStatement(q2, 1004, 1008);
            for (int i = 0; i < prString2.length; ++i) {
                ps.setString(i + 1, prString2[i]);
            }
            rs = ps.executeQuery();
            if (rs.next()) {
                while (en.hasMoreElements()) {
                    column = en.nextElement().toString();
                    final CLOB clob = (CLOB)rs.getClob(column);
                    final Writer writer = clob.getCharacterOutputStream();
                    final Reader src = new CharArrayReader(clobHash.get(column).toString().toCharArray());
                    final char[] buffer = new char[1024];
                    int read = 0;
                    while ((read = src.read(buffer, 0, 1024)) != -1) {
                        writer.write(buffer, 0, read);
                    }
                    src.close();
                    writer.close();
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            con.rollback();
            this.log.debug((Object)e.toString());
            if (rs != null) {
                try {
                    rs.close();
                }
                catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                }
                catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            return;
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                }
                catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                }
                catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
        if (rs != null) {
            try {
                rs.close();
            }
            catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        if (ps != null) {
            try {
                ps.close();
            }
            catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
    
    public void commitChange(final Connection con) {
        try {
            if (con != null) {
                con.commit();
                this.log.debug((Object)"\n***********************\ncommit success\n*******************");
            }
            else {
                this.log.debug((Object)"Connection is null");
            }
        }
        catch (Exception e) {
            this.log.debug((Object)e.toString());
            if (con != null) {
                try {
                    con.setAutoCommit(true);
                    this.log.debug((Object)("con.close(); \uc804 " + this.ds));
                    this.log.debug((Object)("con \uc815\ubcf4 ===> " + con));
                    con.close();
                    this.log.debug((Object)("con.close(); \ud6c4 " + this.ds));
                    this.log.debug((Object)("con \uc815\ubcf4 ===> " + con));
                }
                catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            return;
        }
        finally {
            if (con != null) {
                try {
                    con.setAutoCommit(true);
                    this.log.debug((Object)("con.close(); \uc804 " + this.ds));
                    this.log.debug((Object)("con \uc815\ubcf4 ===> " + con));
                    con.close();
                    this.log.debug((Object)("con.close(); \ud6c4 " + this.ds));
                    this.log.debug((Object)("con \uc815\ubcf4 ===> " + con));
                }
                catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
        if (con != null) {
            try {
                con.setAutoCommit(true);
                this.log.debug((Object)("con.close(); \uc804 " + this.ds));
                this.log.debug((Object)("con \uc815\ubcf4 ===> " + con));
                con.close();
                this.log.debug((Object)("con.close(); \ud6c4 " + this.ds));
                this.log.debug((Object)("con \uc815\ubcf4 ===> " + con));
            }
            catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
    
    public void rollbackChange(final Connection con) {
        try {
            if (con != null) {
                con.rollback();
                this.log.debug((Object)"rollback success");
            }
            else {
                this.log.debug((Object)"Connection is null");
            }
        }
        catch (Exception e) {
            this.log.debug((Object)e.toString());
            if (con != null) {
                try {
                    con.setAutoCommit(true);
                    this.log.debug((Object)("con.close(); \uc804 " + this.ds));
                    this.log.debug((Object)("con \uc815\ubcf4 ===> " + con));
                    con.close();
                    this.log.debug((Object)("con.close(); \ud6c4 " + this.ds));
                    this.log.debug((Object)("con \uc815\ubcf4 ===> " + con));
                }
                catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            return;
        }
        finally {
            if (con != null) {
                try {
                    con.setAutoCommit(true);
                    this.log.debug((Object)("con.close(); \uc804 " + this.ds));
                    this.log.debug((Object)("con \uc815\ubcf4 ===> " + con));
                    con.close();
                    this.log.debug((Object)("con.close(); \ud6c4 " + this.ds));
                    this.log.debug((Object)("con \uc815\ubcf4 ===> " + con));
                }
                catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
        if (con != null) {
            try {
                con.setAutoCommit(true);
                this.log.debug((Object)("con.close(); \uc804 " + this.ds));
                this.log.debug((Object)("con \uc815\ubcf4 ===> " + con));
                con.close();
                this.log.debug((Object)("con.close(); \ud6c4 " + this.ds));
                this.log.debug((Object)("con \uc815\ubcf4 ===> " + con));
            }
            catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
    
    public void loging(final String query, final String[] prString, final Exception e) {
        final StringBuffer buf = new StringBuffer();
        buf.append(this.toString()).append(" | ");
        buf.append(e.toString()).append(" | ");
        buf.append("\nQuery :\n").append(query).append("\ncondition Value :\n");
        if (prString != null) {
            for (int i = 0; i < prString.length; ++i) {
                buf.append(String.valueOf(i) + ":[" + prString[i]).append("]\n");
            }
        }
        buf.append(e.getMessage());
        this.log.debug((Object)buf.toString());
    }
    
    private static void selectClobTest() {
    }
    
    private static void insertClobTest() {
    }
    
    public String sql(final Class<?> c, final String fileName) throws Exception {
        final StringBuffer sb = new StringBuffer();
        InputStream im = null;
        try {
            im = c.getResourceAsStream(fileName);
            final byte[] b = new byte[1024];
            int i = 0;
            while (true) {
                i = im.read(b);
                if (i == -1) {
                    break;
                }
                sb.append(new String(b, 0, i));
            }
            im.close();
        }
        catch (Exception e) {
            throw new FileNotFoundException(String.valueOf(fileName) + "\ud30c\uc77c\uc774 \uc5c6\uc2b5\ub2c8\ub2e4.");
        }
        return sb.toString();
    }
    
    public ArrayList<EmsMetaData> getArrayListMeta() {
        return this.metaHash;
    }
    
    private void connClose(final Connection con) {
        try {
            con.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void main(final String[] args) {
    }
}
