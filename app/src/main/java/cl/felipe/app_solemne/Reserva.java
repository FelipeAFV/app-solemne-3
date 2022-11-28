package cl.felipe.app_solemne;

public class Reserva {

    private int id;
    private int userId;
    private int libroId;

    public Reserva(int userId, int libroId) {
        this.userId = userId;
        this.libroId = libroId;
    }

    public Reserva(int id, int userId, int libroId) {
        this.id = id;
        this.userId = userId;
        this.libroId = libroId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getLibroId() {
        return libroId;
    }

    public void setLibroId(int libroId) {
        this.libroId = libroId;
    }
}
