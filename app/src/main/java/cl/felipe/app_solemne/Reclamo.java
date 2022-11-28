package cl.felipe.app_solemne;

public class Reclamo {

    private int id;
    private String comment;
    private int usuarioId;
    private int libroId;



    public Reclamo(String comment, int usuarioId, int libroId) {
        this.comment = comment;
        this.usuarioId = usuarioId;
        this.libroId = libroId;
    }

    public Reclamo(int id, String comment) {
        this.id = id;
        this.comment = comment;
    }

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

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public int getLibroId() {
        return libroId;
    }

    public void setLibroId(int libroId) {
        this.libroId = libroId;
    }
}
