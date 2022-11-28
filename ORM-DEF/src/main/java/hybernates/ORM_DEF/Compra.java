package hybernates.ORM_DEF;

import java.time.LocalDate;

public class Compra {

    int idCompra;
    int idBitllet;
    int idViatge;
    int idClient;
    LocalDate dataCompra;
    double preu;
    String nomPassatger;
    String dniPassatger;

    public Compra() {
    }

    public Compra(int idBitllet, int idViatge, int idClient, LocalDate dataCompra, double preu, String nomPassatger, String dniPassatger) {
        this.idBitllet = idBitllet;
        this.idViatge = idViatge;
        this.idClient = idClient;
        this.dataCompra = dataCompra;
        this.preu = preu;
        this.nomPassatger = nomPassatger;
        this.dniPassatger = dniPassatger;
    }
    public Compra(int idCompra,int idBitllet, int idViatge, int idClient, LocalDate dataCompra, double preu, String nomPassatger, String dniPassatger) {
        this.idCompra=idCompra;
        this.idBitllet = idBitllet;
        this.idViatge = idViatge;
        this.idClient = idClient;
        this.dataCompra = dataCompra;
        this.preu = preu;
        this.nomPassatger = nomPassatger;
        this.dniPassatger = dniPassatger;
    }

    public int getIdBitllet() {
        return idBitllet;
    }

    public void setIdBitllet(int idBitllet) {
        this.idBitllet = idBitllet;
    }

    public int getIdViatge() {
        return idViatge;
    }

    public void setIdViatge(int idViatge) {
        this.idViatge = idViatge;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public LocalDate getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(LocalDate dataCompra) {
        this.dataCompra = dataCompra;
    }

    public double getPreu() {
        return preu;
    }

    public void setPreu(double preu) {
        this.preu = preu;
    }

    public String getNomPassatger() {
        return nomPassatger;
    }

    public void setNomPassatger(String nomPassatger) {
        this.nomPassatger = nomPassatger;
    }

    public String getDniPassatger() {
        return dniPassatger;
    }

    public void setDniPassatger(String dniPassatger) {
        this.dniPassatger = dniPassatger;
    }

    public int getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
    }
}
