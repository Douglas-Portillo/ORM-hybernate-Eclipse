package hybernates.ORM_DEF;

public class FacEquip {
    int id;
    int idVia;
    int idCli;
    int idEqui;

    public FacEquip() {
    }

    public FacEquip(int id, int idVia, int idCli, int idEqui) {
        this.id = id;
        this.idVia = idVia;
        this.idCli = idCli;
        this.idEqui = idEqui;
    }
    public FacEquip( int idVia, int idCli, int idEqui) {
        this.idVia = idVia;
        this.idCli = idCli;
        this.idEqui = idEqui;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdVia() {
        return idVia;
    }

    public void setIdVia(int idVia) {
        this.idVia = idVia;
    }

    public int getIdCli() {
        return idCli;
    }

    public void setIdCli(int idCli) {
        this.idCli = idCli;
    }

    public int getIdEqui() {
        return idEqui;
    }

    public void setIdEqui(int idEqui) {
        this.idEqui = idEqui;
    }
}
