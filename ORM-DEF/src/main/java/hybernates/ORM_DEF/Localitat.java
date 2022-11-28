package hybernates.ORM_DEF;

public class Localitat {
    int id;
    String nom;
    String pais;
    String abreviacio;

    public Localitat() {
    }

    public Localitat(int id, String nom, String pais, String abreviacio) {
        this.id = id;
        this.nom = nom;
        this.pais = pais;
        this.abreviacio = abreviacio;
    }
    public Localitat(String nom, String pais, String abreviacio) {
        this.id = id;
        this.nom = nom;
        this.pais = pais;
        this.abreviacio = abreviacio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getAbreviacio() {
        return abreviacio;
    }

    public void setAbreviacio(String abreviacio) {
        this.abreviacio = abreviacio;
    }
}
