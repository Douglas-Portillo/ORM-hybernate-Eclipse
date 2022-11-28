package Implementacions;

import Interficies.DAO;
import hybernates.ORM_DEF.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Implementacions implements DAO {

    @Override
    public ArrayList<Client> TotsCli(Connection con) {
        ArrayList<Client>clients=new ArrayList<>();
        try {
            Statement stmt = con.createStatement();
            ResultSet rs= stmt.executeQuery("Select * from client");
            rs.getRow();
            while (rs.next()){
                clients.add(new Client(rs.getInt("id_client"),rs.getString("dni"),rs.getString("nom"),(rs.getDate("data_naix").toLocalDate()),rs.getString("mail"),rs.getString("telefon"),rs.getBoolean("admin")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return clients;
    }

    @Override
    public Client cercaClient(String dni, Connection con) {
        Client c;
        try {
            Statement stmt=con.createStatement();
            ResultSet rs= stmt.executeQuery("Select * from client where dni='"+dni+"'");
            rs.getRow();
            rs.next();
             c=new Client(rs.getInt("id_client"),rs.getString("dni"),rs.getString("nom"),(rs.getDate("data_naix").toLocalDate()),rs.getString("mail"),rs.getString("telefon"),rs.getBoolean("admin"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return c;
    }
    public Client cercaClient(int id, Connection con) {
        Client c;
        try {
            Statement stmt=con.createStatement();
            ResultSet rs= stmt.executeQuery("Select * from client where id_client="+id);
            rs.getRow();

            c=new Client(rs.getInt("id_client"),rs.getString("dni"),rs.getString("nom"),rs.getDate("data_naix").toLocalDate(),rs.getString("mail"),rs.getString("telefon"),rs.getBoolean("admin"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return c;
    }


    @Override
    public boolean createClient(Client cli, Connection con) {
        try {
            Statement stmt=con.createStatement();
            stmt.executeUpdate("Insert into client (dni,nom,data_naix,mail,telefon) values('"+cli.getDni()+"','"+cli.getNom()+"','"+cli.getDataNaix()+"','"+cli.getTelefon()+"','"+cli.getEmail()+"')");
        }
        catch(Exception a) {
            return false;
        }

        return true;
    }

    @Override
    public boolean updateClient(Client cli, Connection con) {
        try {
            Statement stmt=con.createStatement();
            stmt.executeUpdate("Update client SET (dni,nom,data_naix,mail,telefon) = ('"+cli.getDni()+"','"+cli.getNom()+"','"+cli.getDataNaix()+"','"+cli.getTelefon()+"','"+cli.getEmail()+"') where id_client="+cli.getId());
        }
        catch(Exception a) {
            return false;
        }

        return true;
    }

    @Override
    public boolean deleteClient(Client cli, Connection con) {
        try {
            Statement stmt=con.createStatement();
            stmt.executeUpdate("Delete from client where id_client="+cli.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    @Override
    public ArrayList<Bitllet> TotsBit(Connection con) {
        ArrayList<Bitllet>bitllets=new ArrayList<>();
        try {
            Statement stmt = con.createStatement();
            ResultSet rs= stmt.executeQuery("Select * from bitllets");
            rs.getRow();
            while (rs.next()){
                bitllets.add(new Bitllet(rs.getInt("id_bitllet"),rs.getDouble("preu"),rs.getInt("tipus_s"),rs.getInt("id_viatge")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return bitllets;
    }

    @Override
    public Bitllet cercaBitllet(int id, Connection con) {
        Bitllet b;
        try {
            Statement stmt=con.createStatement();
            ResultSet rs= stmt.executeQuery("Select * from bitllets where id_bitllet="+id);
            rs.getRow();
            rs.next();
            b=new Bitllet(rs.getInt("id_bitllet"),rs.getDouble("preu"),rs.getInt("tipus_s"),rs.getInt("id_viatge"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return b;
    }

    @Override
    public boolean createBitllet(Bitllet bit, Connection con) {
        try {
            Statement stmt=con.createStatement();
            stmt.executeUpdate("Insert into bitllets (preu,tipus_s,id_viatge) values("+bit.getPreu()+","+bit.getTipusSeient()+","+bit.getIdViatge()+")");
        }
        catch(Exception a) {
            return false;
        }

        return true;
    }

    @Override
    public boolean updateBitllet(Bitllet bit, Connection con) {
        try {
            Statement stmt=con.createStatement();
            stmt.executeUpdate("Update bitllets SET (preu,tipus_s,id_viatge) = ("+bit.getPreu()+","+bit.getTipusSeient()+","+bit.getIdViatge()+") where id_bitllet="+bit.getId());
        }
        catch(Exception a) {
            return false;
        }

        return true;
    }

    @Override
    public boolean deleteBitllet(Bitllet bit, Connection con) {
        try {
            Statement stmt=con.createStatement();
            stmt.executeUpdate("Delete from bitllets where id_bitllet="+bit.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    @Override
    public ArrayList<Compra> TotsCom(Connection con) {
        ArrayList<Compra>compres=new ArrayList<>();
        try {
            Statement stmt = con.createStatement();
            ResultSet rs= stmt.executeQuery("Select * from compres");
            rs.getRow();
            while (rs.next()){
                compres.add(new Compra(rs.getInt("id_compra"),rs.getInt("id_bitllet"),rs.getInt("id_viatge"),rs.getInt("id_client"),rs.getDate("data_compra").toLocalDate(),rs.getDouble("preu"),rs.getString("nom_viatger"),rs.getString("dni_viatger")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return compres;
    }

    @Override
    public Compra cercaCompra(int id, Connection con) {
        Compra c;
        try {
            Statement stmt=con.createStatement();
            ResultSet rs= stmt.executeQuery("Select *  from compres where id_compra="+id);
            rs.getRow();
            rs.next();
            c=new Compra(rs.getInt("id_compra"),rs.getInt("id_bitllet"),rs.getInt("id_viatge"),rs.getInt("id_client"),rs.getDate("data_compra").toLocalDate(),rs.getDouble("preu"),rs.getString("nom_viatger"),rs.getString("dni_viatger"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return c;
    }

    @Override
    public boolean createCompra(Compra com, Connection con) {
        try {
            Statement stmt=con.createStatement();
            stmt.executeUpdate("Insert into compres (id_bitllet,id_viatge,id_client,data_compra,preu,nom_viatger,dni_viatger) values('"+com.getIdBitllet()+"','"+com.getIdViatge()+"','"+com.getIdClient()+"','"+com.getDataCompra()+"','"+com.getPreu()+"','"+com.getNomPassatger()+"','"+com.getDniPassatger()+"')");
        }
        catch(Exception a) {
            return false;
        }

        return true;
    }

    @Override
    public boolean updateCompra(Compra com, Connection con) {
        try {
            Statement stmt=con.createStatement();
            stmt.executeUpdate("Update compres SET (id_compra,id_bitllet,id_viatge,id_client,data_compra,preu,nom_viatger,dni_viatger) = ("+com.getIdCompra()+","+com.getIdBitllet()+","+com.getIdViatge()+","+com.getIdClient()+",'"+com.getDataCompra()+"',"+com.getPreu()+",'"+com.getNomPassatger()+"','"+com.getDniPassatger()+"') where id_bitllet="+com.getIdBitllet()+" AND id_viatge="+com.getIdViatge());
        }
        catch(Exception a) {
            return false;
        }

        return true;
    }

    @Override
    public boolean deleteCompra(Compra com, Connection con) {
        try {
            Statement stmt=con.createStatement();
            stmt.executeUpdate("Delete from compres where id_bitllet="+com.getIdBitllet()+" AND id_viatge= "+com.getIdViatge());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    @Override
    public ArrayList<Equipatge> TotsEquip(Connection con) {
        ArrayList<Equipatge>equipatges=new ArrayList<>();
        try {
            Statement stmt = con.createStatement();
            ResultSet rs= stmt.executeQuery("Select * from equipatge");
            rs.getRow();
            while (rs.next()){
                equipatges.add(new Equipatge(rs.getInt("id_equipatge"),rs.getString("nom"),rs.getDouble("pes"),rs.getDouble("preu")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return equipatges;
    }

    @Override
    public Equipatge cercaEquipatge(int id, Connection con) {
        Equipatge q;
        try {
            Statement stmt=con.createStatement();
            ResultSet rs= stmt.executeQuery("Select * from equipatge where id_equipatge="+id);
            rs.getRow();
            rs.next();
            q=new Equipatge(rs.getInt("id_equipatge"),rs.getString("nom"),rs.getDouble("pes"),rs.getDouble("preu"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return q;
    }

    @Override
    public boolean createEquipatge(Equipatge equ, Connection con) {
        try {
            Statement stmt=con.createStatement();
            stmt.executeUpdate("Insert into equipatge values("+equ.getId()+",'"+equ.getNom()+"',"+equ.getPes()+","+equ.getPreu()+")");
        }
        catch(Exception a) {
            return false;
        }

        return true;
    }

    @Override
    public boolean updateEquipatge(Equipatge equ, Connection con) {
        try {
            Statement stmt=con.createStatement();
            stmt.executeUpdate("Update equipatge SET("+equ.getId()+",'"+equ.getNom()+"',"+equ.getPes()+","+equ.getPreu()+") where id_equipatge="+equ.getId());
        }
        catch(Exception a) {
            return false;
        }

        return true;
    }

    @Override
    public boolean deleteEquipatge(Equipatge equ, Connection con) {
        try {
            Statement stmt=con.createStatement();
            stmt.executeUpdate("Delete from equipatge where id_equipatge="+equ.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    public ArrayList<FacEquip> TotsFequip(Connection con) {
        ArrayList<FacEquip>facEquips=new ArrayList<>();
        try {
            Statement stmt = con.createStatement();
            ResultSet rs= stmt.executeQuery("Select * from factura_equipatge");
            rs.getRow();
            while (rs.next()){
                facEquips.add(new FacEquip(rs.getInt("id_factura"),rs.getInt("id_viatge"),rs.getInt("id_client"),rs.getInt("id_equipatge")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return facEquips;
    }

    @Override
    public FacEquip cercaFacEquipatge(int id, Connection con) {
        FacEquip f;
        try {
            Statement stmt=con.createStatement();
            ResultSet rs= stmt.executeQuery("Select * from factura_equipatge where id_factura =" +id);
            rs.getRow();
            rs.next();
            f=new FacEquip(rs.getInt("id_factura"),rs.getInt("id_viatge"),rs.getInt("id_client"),rs.getInt("id_equipatge"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return f;
    }

    @Override
    public boolean createFacEquipatge(FacEquip feq, Connection con) {
        try {
            Statement stmt=con.createStatement();
            stmt.executeUpdate("Insert into factura_equipatge(id_viatge,id_client,id_equipatge) values("+feq.getIdVia()+","+feq.getIdCli()+","+feq.getIdEqui()+")");
        }
        catch(Exception a) {
            return false;
        }

        return true;
    }

    @Override
    public boolean updateFacEquipatge(FacEquip feq, Connection con) {
        try {
            Statement stmt=con.createStatement();
            stmt.executeUpdate("Update factura_equipatge SET ("+feq.getId()+","+feq.getIdVia()+","+feq.getIdCli()+","+feq.getIdEqui()+") where id_factura="+feq.getId()+"");
        }
        catch(Exception a) {
            return false;
        }

        return true;
    }

    @Override
    public boolean deleteFacEquipatge(FacEquip feq, Connection con) {
        try {
            Statement stmt=con.createStatement();
            stmt.executeUpdate("Delete from factura_equipatge where id_factura="+feq.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    @Override
    public ArrayList<Localitat> TotsLoc(Connection con) {
        ArrayList<Localitat>localitats=new ArrayList<>();
        try {
            Statement stmt = con.createStatement();
            ResultSet rs= stmt.executeQuery("Select * from localitats");
            rs.getRow();
            while (rs.next()){
                localitats.add(new Localitat(rs.getInt("id_localitat"),rs.getString("nom"),rs.getString("pais"),rs.getString("abreviacio")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return localitats;
    }

    @Override
    public Localitat cercaLocalitat(int id, Connection con) {
        Localitat l;
        try {
            Statement stmt=con.createStatement();
            ResultSet rs= stmt.executeQuery("Select * from localitats where id_localitat="+id);
            rs.getRow();
            rs.next();
            l=new Localitat(rs.getInt("id_localitat"),rs.getString("nom"),rs.getString("pais"),rs.getString("abreviacio"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return l;
    }

    @Override
    public boolean createLocalitat(Localitat loc, Connection con) {
        try {
            Statement stmt=con.createStatement();
            stmt.executeUpdate("Insert into localitats (nom,pais,abreviacio) values('"+loc.getNom()+"','"+loc.getPais()+"','"+loc.getAbreviacio()+"')");
        }
        catch(Exception a) {
            return false;
        }

        return true;
    }

    @Override
    public boolean updateLocalitat(Localitat loc, Connection con) {
        try {
            Statement stmt=con.createStatement();
            stmt.executeUpdate("Update localitats SET (nom,pais,abreviacio) = ('"+loc.getNom()+"','"+loc.getPais()+"','"+loc.getAbreviacio()+"') where id_localitat="+loc.getId()+"");
        }
        catch(Exception a) {
            return false;
        }

        return true;
    }

    @Override
    public boolean deleteLocalitat(Localitat loc, Connection con) {
        try {
            Statement stmt=con.createStatement();
            stmt.executeUpdate("Delete from localitats where id_localitat="+loc.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    @Override
    public ArrayList<Transport> TotsTran(Connection con) {
        ArrayList<Transport>transports=new ArrayList<>();
        try {
            Statement stmt = con.createStatement();
            ResultSet rs= stmt.executeQuery("Select * from transport");
            rs.getRow();
            while (rs.next()){
                transports.add(new Transport(rs.getInt("id_transport"),rs.getInt("s_normal"),rs.getInt("s_preferent"),rs.getString("nom"),rs.getInt("max_pes")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return transports;
    }

    @Override
    public Transport cercaTransport(int id, Connection con) {
        Transport t;
        try {
            Statement stmt=con.createStatement();
            ResultSet rs= stmt.executeQuery("Select * from transport where id_transport="+id);
            rs.getRow();
            rs.next();
            t=new Transport(rs.getInt("id_transport"),rs.getInt("s_normal"),rs.getInt("s_preferent"),rs.getString("nom"),rs.getInt("max_pes"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return t;
    }

    @Override
    public boolean createTransport(Transport tra, Connection con) {
        try {
            Statement stmt=con.createStatement();
            stmt.executeUpdate("Insert into transport (s_normal,s_preferent,nom,max_pes) values("+tra.getsNormal()+","+tra.getsPreferent()+",'"+tra.getNom()+"',"+tra.getMaxPes()+")");
        }
        catch(Exception a) {
            return false;
        }

        return true;
    }

    @Override
    public boolean updateTransport(Transport tra, Connection con) {
        try {
            Statement stmt=con.createStatement();
            stmt.executeUpdate("Update transport SET (s_normal,s_preferent,nom) = ("+tra.getsNormal()+","+tra.getsPreferent()+",'"+tra.getNom()+"') where id_transport="+tra.getId());
        }
        catch(Exception a) {
            return false;
        }

        return true;
    }

    @Override
    public boolean deleteTransport(Transport tra, Connection con) {
        try {
            Statement stmt=con.createStatement();
            stmt.executeUpdate("Delete from transport where id_transport="+tra.getId()+"");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    @Override
    public ArrayList<Viatge> TotsVia(Connection con) {
        ArrayList<Viatge>viatges=new ArrayList<>();
        try {
            Statement stmt = con.createStatement();
            ResultSet rs= stmt.executeQuery("Select * from viatge");
            rs.getRow();
            while (rs.next()){
                viatges.add(new Viatge(rs.getInt("id_viatge"),rs.getInt("id_origen"),rs.getInt("id_desti"),rs.getTimestamp("data").toLocalDateTime(),rs.getInt("id_transport"),rs.getBoolean("habilitat")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return viatges;
    }

    @Override
    public Viatge cercaViatge(int id, Connection con) {
        Viatge v;
        try {
            Statement stmt=con.createStatement();
            ResultSet rs= stmt.executeQuery("Select * from viatge where id_viatge="+id);
            rs.getRow();
            rs.next();
            v=new Viatge(rs.getInt("id_viatge"),rs.getInt("id_origen"),rs.getInt("id_desti"),rs.getTimestamp("data").toLocalDateTime(),rs.getInt("id_transport"),rs.getBoolean("habilitat"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return v;    }

    @Override
    public boolean createViatge(Viatge via, Connection con) {
        try {
            Statement stmt=con.createStatement();
            stmt.executeUpdate("Insert into viatge (id_origen,id_desti,data,id_transport,habilitat) values("+via.getIdOrigen()+","+via.getIdDesti()+",'"+via.getDataHora()+"',"+via.getIdTransport()+","+via.isHabilitat()+")");
        }
        catch(Exception a) {
            return false;
        }

        return true;
    }
    @Override
    public boolean updateViatge(Viatge via, Connection con) {
        try {
            Statement stmt=con.createStatement();
            stmt.executeUpdate("Update viatge SET (id_origen,id_desti,data,id_transport,habilitat) = ("+via.getIdOrigen()+","+via.getIdDesti()+",'"+via.getDataHora()+"',"+via.getIdTransport()+","+via.isHabilitat()+") where id_viatge='"+via.getIdViatge()+"'");
        }
        catch(Exception a) {
            return false;
        }
        return true;
    }
    @Override
    public boolean deleteViatge(Viatge via, Connection con) {
        try {
            Statement stmt=con.createStatement();
            stmt.executeUpdate("Delete from viatge where id_viatge="+via.getIdViatge());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }
}
