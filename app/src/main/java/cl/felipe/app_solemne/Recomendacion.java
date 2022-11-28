package cl.felipe.app_solemne;

public class Recomendacion {


    private int id;
    private String comment;

    private int autorId;

    private int userId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getAutorId() {
        return autorId;
    }

    public void setAutorId(int autorId) {
        this.autorId = autorId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Recomendacion(int id, String comment, int autorId, int userId) {
        this.id = id;
        this.comment = comment;
        this.autorId = autorId;
        this.userId = userId;
    }

    public Recomendacion(String comment, int autorId, int userId) {
        this.comment = comment;
        this.autorId = autorId;
        this.userId = userId;
    }

    public Recomendacion(int id, String comment) {
        this.id = id;
        this.comment = comment;
    }
}
