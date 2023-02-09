//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import entity.User;
import utils.DataSource;

public class ServiceUser implements IService<User> {
    private Connection conn = DataSource.getInstance().getCnx();

    public ServiceUser() {
    }

    public void insert(User t) {
        String var10000 = t.getNom();
        String requete = "insert into personne (nom,prenom,age) values ('" + var10000 + "','" + t.getPrenom() + "'," + t.getAge() + ")";

        try {
            Statement st = this.conn.createStatement();
            st.executeUpdate(requete);
        } catch (SQLException var4) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, (String)null, var4);
        }

    }

    public void insertPst(User p) {
        String requete = "insert into personne(nom,prenom,age) values(?,?,?)";

        try {
            PreparedStatement pst = this.conn.prepareStatement(requete);
            pst.setString(1, p.getNom());
            pst.setString(2, p.getPrenom());
            pst.setInt(3, p.getAge());
            pst.executeUpdate();
        } catch (SQLException var4) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, (String)null, var4);
        }

    }

    public void delete(User t) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void update(User t) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<User> readAll() {
        List<User> list = new ArrayList();
        String requete = "select * from personne";

        try {
            Statement st = this.conn.createStatement();
            ResultSet rs = st.executeQuery(requete);

            while(rs.next()) {
                User p = new User(rs.getInt("id"), rs.getString(2), rs.getString("prenom"), rs.getInt("age"));
                list.add(p);
            }
        } catch (SQLException var6) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, (String)null, var6);
        }

        return list;
    }

    public User readById(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
