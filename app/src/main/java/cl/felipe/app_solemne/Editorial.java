package cl.felipe.app_solemne;

public class Editorial {

    private int id;
    private String name;

    private int paisId;

    public Editorial(String name, int paisId) {
        this.name = name;
        this.paisId = paisId;
    }

    public Editorial(int id, String name, int paisId) {
        this.id = id;
        this.name = name;
        this.paisId = paisId;
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

    public int getPaisId() {
        return paisId;
    }

    public void setPaisId(int paisId) {
        this.paisId = paisId;
    }
}
