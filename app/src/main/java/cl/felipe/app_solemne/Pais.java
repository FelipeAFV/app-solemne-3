package cl.felipe.app_solemne;

public class Pais {

    private int id;
    private String name;
    private String abreviation;

    public Pais(int id, String name, String abreviation) {
        this.id = id;
        this.name = name;
        this.abreviation = abreviation;
    }

    public Pais(String name, String abreviation) {
        this.name = name;
        this.abreviation = abreviation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbreviation() {
        return abreviation;
    }

    public void setAbreviation(String abreviation) {
        this.abreviation = abreviation;
    }
}
