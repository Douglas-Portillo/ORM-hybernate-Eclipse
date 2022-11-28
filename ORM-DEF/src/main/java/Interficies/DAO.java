package Interficies;

import hybernates.ORM_DEF.*;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public interface DAO {
    ArrayList<Client> TotsCli(Connection con);
    Client cercaClient(String dni, Connection con);
    Client cercaClient(int id, Connection con);

    boolean createClient(Client cli, Connection con);
    boolean updateClient(Client cli, Connection con);
    boolean deleteClient(Client cli, Connection con);

    ArrayList<Bitllet> TotsBit(Connection con);
    Bitllet cercaBitllet(int id, Connection con);

    boolean createBitllet(Bitllet bit, Connection con);
    boolean updateBitllet(Bitllet bit, Connection con);
    boolean deleteBitllet(Bitllet bit, Connection con);



    ArrayList<Compra> TotsCom(Connection con);
    Compra cercaCompra(int id, Connection con);

    boolean createCompra(Compra com, Connection con);
    boolean updateCompra(Compra com, Connection con);
    boolean deleteCompra(Compra com, Connection con);


    ArrayList<Equipatge> TotsEquip(Connection con);
    Equipatge cercaEquipatge(int id, Connection con);

    boolean createEquipatge(Equipatge equ, Connection con);
    boolean updateEquipatge(Equipatge equ, Connection con);
    boolean deleteEquipatge(Equipatge equ, Connection con);

    ArrayList<FacEquip> TotsFequip(Connection con);
    FacEquip cercaFacEquipatge(int id, Connection con);

    boolean createFacEquipatge(FacEquip feq, Connection con);
    boolean updateFacEquipatge(FacEquip feq, Connection con);
    boolean deleteFacEquipatge(FacEquip feq, Connection con);



    ArrayList<Localitat> TotsLoc(Connection con);
    Localitat cercaLocalitat(int id, Connection con);

    boolean createLocalitat(Localitat loc, Connection con);
    boolean updateLocalitat(Localitat loc, Connection con);
    boolean deleteLocalitat(Localitat loc, Connection con);


    ArrayList<Transport> TotsTran(Connection con);
    Transport cercaTransport(int id, Connection con);

    boolean createTransport(Transport tra, Connection con);
    boolean updateTransport(Transport tra, Connection con);
    boolean deleteTransport(Transport tra, Connection con);


    ArrayList<Viatge> TotsVia(Connection con);
    Viatge cercaViatge(int id, Connection con);

    boolean createViatge(Viatge via, Connection con);
    boolean updateViatge(Viatge via, Connection con);
    boolean deleteViatge(Viatge via, Connection con);



}
