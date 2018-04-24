/*
 * This java source file is generated by DAO4J v1.19
 * Generated on Mon Apr 23 22:57:05 CDT 2018
 * For more information, please contact b-i-d@163.com
 * Please check http://sourceforge.net/projects/dao4j/ for the latest version.
 */

package DAOImpl;

import Modelo.EspiasKey;
import Modelo.Espias;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.ArrayList;
import DAO.EspiasDAO;

/**
 * This class provides methods to populate DB Table of espias
 */
public class EspiasDAOImpl implements EspiasDAO {
    /* SQL to insert data */
    private static final String SQL_INSERT =
        "INSERT INTO espias ("
        + "alias, genero, estado"
        + ") VALUES (?, ?, ?)";

    /* SQL to select data */
    private static final String SQL_SELECT =
        "SELECT "
        + "alias, genero, estado "
        + "FROM espias WHERE "
        + "alias = ?";

    /* SQL to update data */
    private static final String SQL_UPDATE =
        "UPDATE espias SET "
        + "genero = ?, estado = ? "
        + "WHERE "
        + "alias = ?";

    /* SQL to delete data */
    private static final String SQL_DELETE =
        "DELETE FROM espias WHERE "
        + "alias = ?";

    /**
     * Create a new record in Database.
     * @param bean   The Object to be inserted.
     * @param conn   JDBC Connection.
     * @exception    SQLException if something is wrong.
     */
    public void create(Espias bean, Connection conn) throws SQLException {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(SQL_INSERT);
            ps.setString(1, bean.getAlias());
            ps.setString(2, bean.getGenero());
            ps.setShort(3, bean.getEstado());
            ps.executeUpdate();
        }finally {
            close(ps);
        }
    }

    /**
     * Retrive a record from Database.
     * @param beanKey   The PK Object to be retrived.
     * @param conn      JDBC Connection.
     * @exception       SQLException if something is wrong.
     */
    public Espias load(EspiasKey key, Connection conn) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(SQL_SELECT);
            ps.setString(1, key.getAlias());
            rs = ps.executeQuery();
            List results = getResults(rs);
            if (results.size() > 0)
                return (Espias) results.get(0);
            else
                return null;
        }finally {
            close(rs);
            close(ps);
        }
    }

    /**
     * Update a record in Database.
     * @param bean   The Object to be saved.
     * @param conn   JDBC Connection.
     * @exception    SQLException if something is wrong.
     */
    public void update(Espias bean, Connection conn) throws SQLException {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(SQL_UPDATE);
            ps.setString(1, bean.getGenero());
            ps.setShort(2, bean.getEstado());
            ps.setString(3, bean.getAlias());
            ps.executeUpdate();
        }finally {
            close(ps);
        }
    }

    /**
     * Create a new record in Database.
     * @param bean   The PK Object to be deleted.
     * @param conn   JDBC Connection.
     * @exception    SQLException if something is wrong.
     */
    public void delete(EspiasKey key, Connection conn) throws SQLException {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(SQL_DELETE);
            ps.setString(1, key.getAlias());
            ps.executeUpdate();
        }finally {
            close(ps);
        }
    }
    
    /**
     * Populate the ResultSet.
     * @param rs     The ResultSet.
     * @return       The Object to retrieve from DB.
     * @exception    SQLException if something is wrong.
     */
    protected List<Espias> getResults(ResultSet rs) throws SQLException {
        List results = new ArrayList<Espias>();
        while (rs.next()) {
            Espias bean = new Espias();
            bean.setAlias(rs.getString("alias"));
            bean.setGenero(rs.getString("genero"));
            bean.setEstado(rs.getShort("estado"));
            results.add(bean);
        }
        return results;
    }

    /**
     * Close JDBC Statement.
     * @param stmt  Statement to be closed.
     */
    protected void close(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            }catch(SQLException e){}
        }
    }

    /**
     * Close JDBC ResultSet.
     * @param rs  ResultSet to be closed.
     */
    protected void close(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            }catch(SQLException e){}
        }
    }
}