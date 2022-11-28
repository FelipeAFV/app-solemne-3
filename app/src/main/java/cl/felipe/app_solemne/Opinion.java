package cl.felipe.app_solemne;

public class Opinion {

    private int id;

    private String score;

    private String comment;

    private int libroId;

    private int usuarioId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getLibroId() {
        return libroId;
    }

    public void setLibroId(int libroId) {
        this.libroId = libroId;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Opinion(int id, String score, String comment, int libroId, int usuarioId) {
        this.id = id;
        this.score = score;
        this.comment = comment;
        this.libroId = libroId;
        this.usuarioId = usuarioId;
    }

    public Opinion(String comment, int libroId, int usuarioId) {
        this.comment = comment;
        this.libroId = libroId;
        this.usuarioId = usuarioId;
    }

    public Opinion(int id, String comment) {
        this.id = id;
        this.comment = comment;
    }
}
