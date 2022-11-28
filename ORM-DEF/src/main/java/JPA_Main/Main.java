package JPA_Main;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import hybernates.ORM_DEF.*;

import Implementacions.Implementacions;
import Interficies.DAO;

public class Main {
    static DAO dao=new Implementacions();
     static Connection con;

    static {
        try {
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/agencia_viatges","postgres","mcgastron99");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static void main(String[] args) throws SQLException {
    	
    	
        Scanner lec=new Scanner(System.in);

        int opcio = 0;
       do{
           System.out.println("\t[1] Iniciar sessió\n\t[2] Registrar");
           opcio=lec.nextInt();
           lec.nextLine();
           switch (opcio){
               case 1:

                   System.out.println("Dni del usuari: ");
                   String dni=lec.nextLine();
                      while(!userExist(dni)){
                          System.out.println("No existeix l'usuari. Torna a introduir-lo. ");
                          dni=lec.nextLine();
                      }
                      System.out.println("Inici de sessió correcte");
                     Client cli= dao.cercaClient(dni,con);
                     if(cli.isAdmin()){
                         menuAdmin();
                     }
                     else{
                         menuUser(cli);
                     }
                   break;


                     case 2:
                   System.out.println("Formulari de registre\n");
                   System.out.println("Nom:");
                   String nom=lec.nextLine();
                   System.out.println("Dni: ");
                   dni=lec.nextLine();
                   System.out.println("Data de Naixament (DD-MM-AAAA)");
                   String data=lec.nextLine();
                   System.out.println("Email: ");
                   String email=lec.next();
                   System.out.println("Telefon: ");
                   String telefon=lec.next();
                   while(userExist(dni)){
                       System.out.println("Dni del usuari: ");
                       dni=lec.nextLine();
                   }
                   String []dataa=data.split("-");
                   Client c=new Client(dni,nom,LocalDate.of(Integer.parseInt(dataa[2]),Integer.parseInt(dataa[1]),Integer.parseInt(dataa[0])),telefon,email,false);
                   dao.createClient(c,con);
           }
        }
        while(opcio!=0);

    }



public static void menuAdmin(){
    Scanner lec=new Scanner(System.in);

    int opcio;
        do{
            System.out.println("\t[1] Visualitza tots els viatges actius\n\t[2] Afegeix viatge\n\t[3] Deshabilitar/Habilitar viatges\n\t[4] Modificar viatges");
            opcio=lec.nextInt();
            lec.nextLine();
            switch (opcio){
                case 1:
                    imprimirViatges();
                    break;
                case 2:
                	
                	//Afegeix viatge
                	EntityManager entity = JPAUtil.getEntityManagerFactory().createEntityManager();
                    
                	ArrayList<Localitat> localitats = dao.TotsLoc(con);
                    ArrayList<Transport> transports = dao.TotsTran(con);
                    int idOrigen = 0;
                    int idDesti=0;
                    int idTransport=0;
                    boolean trobat = false;
                    do{
                        int i=0;
                        System.out.print("Entra l'origen del viatge: ");
                        String origen = lec.nextLine();
                        while (!trobat && i<localitats.size()){
                            if (localitats.get(i).getNom().equalsIgnoreCase(origen))  {
                                trobat=true;
                                idOrigen = localitats.get(i).getId();
                            }
                            i++;
                        }
                    }while (!trobat);
                    trobat = false;
                    do{
                        int i=0;
                        System.out.print("Entra el destí del viatge: ");
                        String desti = lec.nextLine();
                        while (!trobat && i<localitats.size()){
                            if (localitats.get(i).getNom().equalsIgnoreCase(desti)) {
                                idDesti = localitats.get(i).getId();
                                trobat=true;
                            }
                            i++;
                        }
                    }while (!trobat);
                    System.out.println("Entra la data 'DD-MM-AAAA' del viatge");
                    String [] data = lec.nextLine().split("-");
                    System.out.println("Entra l'hora 'HH:MM' del viatge");
                    String [] hora =lec.nextLine().split(":");
                    LocalDateTime dateTime = LocalDateTime.of(LocalDate.of(Integer.parseInt(data[2]),Integer.parseInt(data[1]),Integer.parseInt(data[0])),LocalTime.of(Integer.parseInt(hora[0]),Integer.parseInt(hora[1])));
                    trobat = false;
                    do{
                        int i=0;
                        System.out.print("Entra el transport del viatge: ");
                        String transport = lec.nextLine();
                        while (!trobat && i<transports.size()){
                            if (transports.get(i).getNom().equalsIgnoreCase(transport)) {
                                trobat=true;
                                idTransport = transports.get(i).getId();
                            }
                            i++;
                        }
                    }while (!trobat);
                    boolean habilitat = true;
                    Viatge nouViatge = new Viatge(idOrigen,idDesti,dateTime,idTransport,habilitat);
                   
                    
                    entity.getTransaction().begin();
    				entity.persist(nouViatge);
    				entity.getTransaction().commit();
    				System.out.println("viatge registrat..");
    				
    				Viatge viatges;
    				List<Viatge> llistaViatges = new ArrayList<>();
    				
    				Query query=entity.createQuery("SELECT v FROM Viatge v");
					llistaViatges=query.getResultList();
				
                    System.out.println("Entra el preu del bitllet");
                    double preuN = lec.nextDouble();
                    lec.nextLine();
                    Bitllet nouBitllet = new Bitllet(preuN,1,llistaViatges.get(llistaViatges.size()-1).getIdViatge());
                    dao.createBitllet(nouBitllet,con);
                    break;
                case 3:
                    imprimirViatges();
                    System.out.print("Entra l'ID del viatge que vols deshabilitar/habilitar: ");
                    int id = lec.nextInt();
                    lec.nextLine();
                    Viatge v = dao.cercaViatge(id,con);
                    if (v.isHabilitat()){
                        v.setHabilitat(false);
                    }
                    else v.setHabilitat(true);
                    dao.updateViatge(v,con);
                    break;
                case 4:
                    imprimirViatges();
                    System.out.println("\t[1] Canviar origen\n\t[2] Canviar destí\n\t[3] Canviar data\n\t[4] Canviar transport\n\t[5] Esborrar viatge");
                    int canviarViatge = lec.nextInt();
                    lec.nextLine();
                    switch (canviarViatge){
                        case 1:
                            System.out.println("Entra l'ID del viatge que vols canviar ");
                            int idO = lec.nextInt();
                            lec.nextLine();
                            Viatge vO = dao.cercaViatge(idO,con);
                            trobat = false;
                            localitats=dao.TotsLoc(con);
                            idOrigen=0;
                            do{
                                int i=0;
                                System.out.print("Entra l'origen del viatge: ");
                                String origen = lec.nextLine();
                                while (!trobat && i<localitats.size()){
                                    if (localitats.get(i).getNom().equalsIgnoreCase(origen))  {
                                        trobat=true;
                                        idOrigen = localitats.get(i).getId();
                                    }
                                    i++;
                                }
                            }while (!trobat);
                            vO.setIdOrigen(idOrigen);
                            dao.updateViatge(vO,con);
                            break;
                        case 2:
                            System.out.println("Entra l'ID del viatge que vols canviar ");
                            int idD = lec.nextInt();
                            lec.nextLine();
                            Viatge vD = dao.cercaViatge(idD,con);
                            trobat = false;
                            localitats=dao.TotsLoc(con);
                            idDesti=0;
                            do{
                                int i=0;
                                System.out.print("Entra el destí del viatge: ");
                                String desti = lec.nextLine();
                                while (!trobat && i<localitats.size()){
                                    if (localitats.get(i).getNom().equalsIgnoreCase(desti))  {
                                        trobat=true;
                                        idDesti = localitats.get(i).getId();
                                    }
                                    i++;
                                }
                            }while (!trobat);
                            vD.setIdDesti(idDesti);
                            dao.updateViatge(vD,con);
                            break;
                        case 3:
                            System.out.println("Entra l'ID del viatge que vols canviar ");
                            int idH = lec.nextInt();
                            lec.nextLine();
                            Viatge vH = dao.cercaViatge(idH,con);
                            System.out.println("Entra la data 'DD-MM-AAAA' del viatge");
                            String [] dataH = lec.nextLine().split("-");
                            System.out.println("Entra l'hora 'HH:MM' del viatge");
                            String [] horaH =lec.nextLine().split(":");
                            LocalDateTime dateTimeH = LocalDateTime.of(LocalDate.of(Integer.parseInt(dataH[2]),Integer.parseInt(dataH[1]),Integer.parseInt(dataH[0])),LocalTime.of(Integer.parseInt(horaH[0]),Integer.parseInt(horaH[1])));
                            vH.setDataHora(dateTimeH);
                            dao.updateViatge(vH,con);
                            break;
                        case 4:
                            System.out.println("Entra l'ID del viatge que vols canviar ");
                            int idT = lec.nextInt();
                            lec.nextLine();
                            Viatge vT = dao.cercaViatge(idT,con);
                            trobat = false;
                            transports=dao.TotsTran(con);
                            idTransport=0;
                            do{
                                int i=0;
                                System.out.print("Entra el transport del viatge: ");
                                String transport = lec.nextLine();
                                while (!trobat && i<transports.size()){
                                    if (transports.get(i).getNom().equalsIgnoreCase(transport)) {
                                        trobat=true;
                                        idTransport = transports.get(i).getId();
                                    }
                                    i++;
                                }
                            }while (!trobat);
                            vT.setIdTransport(idTransport);
                            dao.updateViatge(vT,con);
                            break;
                        case 5:
                            System.out.println("Entra l'ID del viatge que vols esborrar ");
                            int idE = lec.nextInt();
                            lec.nextLine();
                            ArrayList<Bitllet> bitllets = dao.TotsBit(con);
                            boolean trobatB = false;
                            int i=0;
                            while (!trobatB && i< bitllets.size()){
                                if (bitllets.get(i).getIdViatge()==idE) trobatB = true;
                                else i++;
                            }
                            dao.deleteBitllet(dao.cercaBitllet(bitllets.get(i).getId(),con),con);
                            dao.deleteViatge(dao.cercaViatge(idE,con),con);

                    }
            }
    }while(opcio!=0);
}
public static void menuUser(Client c){
    Scanner lec=new Scanner(System.in);

    int opcio;
    do{
        System.out.println("\t[1] Visualitza viatges \n\t[2] Comprar  bitllets \n\t[3] Editar els teus bitllets");

        opcio=lec.nextInt();
        lec.nextLine();
        switch (opcio){
            case 1:
                imprimirViatges();
                break;
            case 2:
                ArrayList<Viatge> viatgeList = dao.TotsVia(con);
                String origen;
                System.out.println("Introdueix la teva ciutat de sortida");
                origen=lec.nextLine();
                for(Viatge v:viatgeList){
                    if (dao.cercaLocalitat(v.getIdOrigen(),con).getNom().equalsIgnoreCase(origen) && LocalDateTime.now().isBefore(v.getDataHora()) && v.isHabilitat()) {
                        System.out.println("\n---------------");
                        System.out.println(dao.cercaTransport(v.getIdTransport(), con).getNom());
                        System.out.print(dao.cercaLocalitat(v.getIdOrigen(),con).getNom() + " ---> " + dao.cercaLocalitat(v.getIdDesti(),con).getNom());
                        System.out.println("     "+v.getDataHora());
                        System.out.println("Per comprar bitllets d'aquest viatge copia aquest codi: "+v.getIdViatge());
                    }
                }
                int codi=0;
                System.out.println("Enganxa el codi que has copiat: ");
                codi= lec.nextInt();
                lec.nextLine();

                ArrayList<Bitllet>bitllets=dao.TotsBit(con);
                boolean trobat=false;
                Bitllet b = null;
                int i=0;
                while(!trobat && i< bitllets.size()){
                    if(bitllets.get(i).getIdViatge()==codi){
                        b=bitllets.get(i);
                        trobat=true;
                    }
                    else i++;
                }
                Viatge v = dao.cercaViatge(codi,con);
                Transport t = dao.cercaTransport(v.getIdTransport(),con);
                int seients= t.getsNormal() + t.getsPreferent();
                 ArrayList<Compra>compres = dao.TotsCom(con);
                 int dispo=seients;
                 for(Compra co:compres){
                     if(co.getIdBitllet()==b.getId()){
                         dispo--;
                     }
                 }
                 System.out.println("Actualment queden "+dispo+" bitllets disponibles");
                 System.out.println("Preu per bitllet "+b.getPreu()+"€");
                 System.out.println("Vols comprar-ne? (S/N)");
                 String resp="";
                 resp=lec.next();
                 if (resp.equalsIgnoreCase("S")){
                         System.out.println("Quants bitllets vols comprar?");
                            int qB=lec.nextInt();
                            lec.nextLine();
                            if(qB<=dispo){
                               System.out.println(b.getPreu()*qB+"€");
                               for (int j=0;j<qB;j++){
                                   double pest=0;
                                   System.out.println("Introdueix el nom del passatger");
                                   String nom=lec.nextLine();
                                   System.out.println("Introdueix el dni del passatger");
                                   String dni=lec.nextLine();
                                   ArrayList<FacEquip>facEquipsM=dao.TotsFequip(con);
                                       System.out.println("Portarà maletes? (S/N)");
                                       resp=lec.nextLine();
                                       if (resp.equalsIgnoreCase("S")){
                                           Compra com=new Compra(b.getId(),v.getIdViatge(),c.getId(),LocalDate.now(),b.getPreu(),nom,dni);
                                           ArrayList<Equipatge>equips=dao.TotsEquip(con);
                                           System.out.println("Quantes maletes portarà? Maxim 3 per persona");
                                           int quant=lec.nextInt();
                                           lec.nextLine();

                                           for (int k=0;k<quant;k++){
                                               System.out.println("Tria una maleta: \n");
                                               for(Equipatge e:equips) {
                                                   System.out.println(e.getId() + " - " + e.getNom()+"    "+e.getPreu()+"€");
                                               }
                                               int mal=lec.nextInt();
                                               lec.nextLine();
                                               FacEquip fe;
                                               fe=new FacEquip(v.getIdViatge(),c.getId(),mal);
                                               pest=pest+dao.cercaEquipatge(mal,con).getPes();
                                               if(pest<=dao.cercaTransport(v.getIdTransport(),con).getMaxPes()){
                                                   dao.createFacEquipatge(fe,con);
                                                   com.setPreu(com.getPreu()+dao.cercaEquipatge(mal,con).getPreu());
                                               }
                                               else{
                                                   System.out.println("Supera el pes maxim del transport");
                                                    quant=quant-3;
                                               }
                                           }
                                           dao.createCompra(com,con);
                                       }
                                       else {
                                           Compra com=new Compra(b.getId(),v.getIdViatge(),c.getId(),LocalDate.now(),b.getPreu(),nom,dni);
                                           dao.createCompra(com,con);
                                       }
                               }
                            }
                            else{
                                System.out.println("No hi ha tants bitlllets disponibles");
                            }
                 }
                 else System.out.println("Operació cancel·lada correctament.");
                break;
            case 3:
                System.out.println("\t[1] Eliminar compra de bitllet \n\t[2] Eliminar maleta facturada \n\t[3] Facturar una altra maleta");
                int menuC = lec.nextInt();
                lec.nextLine();

                switch (menuC){
                    case 1:
                        ArrayList<Compra>compras=dao.TotsCom(con);
                        ArrayList<Compra>compraC= new ArrayList<>();
                        for (Compra co:compras){
                            if (co.getIdClient()==c.getId()){
                                compraC.add(co);
                            }
                        }
                        for (Compra com:compraC){
                            System.out.print(dao.cercaLocalitat(dao.cercaViatge(com.getIdViatge(),con).getIdOrigen(),con).getNom()+"------>"+dao.cercaLocalitat(dao.cercaViatge(com.getIdViatge(),con).getIdDesti(),con).getNom());
                            System.out.println("     ID: "+com.getIdCompra()+"     "+com.getNomPassatger());
                        }
                        System.out.println("Entra la ID per esborrar la compra");
                        int idE = lec.nextInt();
                        lec.nextLine();
                        dao.deleteCompra(dao.cercaCompra(idE,con),con);
                        break;
                    case 2:
                        ArrayList<FacEquip>facEquips=dao.TotsFequip(con);
                        ArrayList<FacEquip>facEqC= new ArrayList<>();
                        for (FacEquip fe:facEquips){
                            if (fe.getIdCli()==c.getId()){
                                facEqC.add(fe);
                            }
                        }
                        for (FacEquip faq:facEqC){
                            System.out.print(dao.cercaLocalitat(dao.cercaViatge(faq.getIdVia(),con).getIdOrigen(),con).getNom()+"------>"+dao.cercaLocalitat(dao.cercaViatge(faq.getIdVia(),con).getIdDesti(),con).getNom());
                            System.out.print("     "+dao.cercaEquipatge(faq.getIdEqui(),con).getNom());
                            System.out.println("     ID: "+faq.getId());
                        }
                        System.out.println("Entra la ID per esborrar la compra");
                        int idE2 = lec.nextInt();
                        lec.nextLine();
                        dao.deleteFacEquipatge(dao.cercaFacEquipatge(idE2,con),con);
                        break;
                    case 3:
                        ArrayList<Compra>comprasM=dao.TotsCom(con);
                        ArrayList<Compra>compraCM= new ArrayList<>();
                        for (Compra co:comprasM){
                            if (co.getIdClient()==c.getId()){
                                compraCM.add(co);
                            }
                        }
                        for (Compra com:compraCM){
                            System.out.print(dao.cercaLocalitat(dao.cercaViatge(com.getIdViatge(),con).getIdOrigen(),con).getNom()+"------>"+dao.cercaLocalitat(dao.cercaViatge(com.getIdViatge(),con).getIdDesti(),con).getNom());
                            System.out.println("     ID: "+com.getIdCompra());
                        }
                        System.out.println("Entra la ID per afegir maletes");
                        int idEM = lec.nextInt();
                        lec.nextLine();
                        ArrayList<FacEquip>facEquipsM=dao.TotsFequip(con);
                            System.out.println("Tria una maleta: \n");
                            ArrayList<Equipatge>equips=dao.TotsEquip(con);
                            for(Equipatge e:equips) {
                                System.out.println(e.getId() + " - " + e.getNom()+ "    "+e.getPreu()+"€");
                            }
                            int mal=lec.nextInt();
                            lec.nextLine();
                            FacEquip fe;
                            Viatge vM = dao.cercaViatge(dao.cercaCompra(idEM,con).getIdViatge(),con);
                            fe=new FacEquip(vM.getIdViatge(),c.getId(),mal);
                            double pest=dao.cercaEquipatge(mal,con).getPes();
                            if(pest<=dao.cercaTransport(vM.getIdTransport(),con).getMaxPes()){
                                dao.createFacEquipatge(fe,con);
                                Compra ca = dao.cercaCompra(idEM,con);
                                ca.setPreu(dao.cercaCompra(idEM,con).getPreu()+dao.cercaEquipatge(mal,con).getPreu());
                                dao.updateCompra(ca,con);
                            }
                            else{
                                System.out.println("Supera el pes maxim del transport");
                                break;
                            }
                            dao.createFacEquipatge(fe,con);

                }
                break;
        }
    }while (opcio!=0);
}

















    public static void imprimirViatges(){
        //Només admin
        ArrayList<Viatge> viatgeList = dao.TotsVia(con);

        for(Viatge v:viatgeList){
            System.out.println("\n---------------");
            System.out.println(dao.cercaTransport(v.getIdTransport(),con).getNom());
            System.out.print(dao.cercaLocalitat(v.getIdOrigen(),con).getNom()+" ---> "+dao.cercaLocalitat(v.getIdDesti(),con).getNom());
            System.out.print("      "+v.getDataHora()+ "     ID: "+v.getIdViatge());
            if (v.isHabilitat()){
                System.out.println("     HABILITAT");
            }
            else {
                System.out.println("     NO HABILITAT");
            }

        }
    }

    public static boolean userExist(String d){
        Client c= dao.cercaClient(d,con);
        if (c.getDni().equals(d)){
            return true;
        }else{
            return false;
        }
    }
}