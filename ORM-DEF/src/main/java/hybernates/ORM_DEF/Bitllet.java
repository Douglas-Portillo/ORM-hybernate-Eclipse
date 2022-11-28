package hybernates.ORM_DEF;

public class Bitllet {
    int id;
    double preu;
    int tipusSeient;
    int idViatge;

    public Bitllet() {
    }

    public Bitllet(int id, double preu, int tipusSeient, int idViatge) {
        this.id = id;
        this.preu = preu;
        this.tipusSeient = tipusSeient;
        this.idViatge = idViatge;
    }
    public Bitllet(double preu, int tipusSeient, int idViatge) {
        this.preu = preu;
        this.tipusSeient = tipusSeient;
        this.idViatge = idViatge;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPreu() {
        return preu;
    }

    public void setPreu(double preu) {
        this.preu = preu;
    }

    public int getTipusSeient() {
        return tipusSeient;
    }

    public void setTipusSeient(int tipusSeient) {
        this.tipusSeient = tipusSeient;
    }

    public int getIdViatge() {
        return idViatge;
    }

    public void setIdViatge(int idViatge) {
        this.idViatge = idViatge;
    }
}
