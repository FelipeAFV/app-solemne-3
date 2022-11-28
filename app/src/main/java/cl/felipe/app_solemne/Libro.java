package cl.felipe.app_solemne;

public class Libro {

    private int id;
    private String name;
    private String editorial;

    private int editorialId;
    private int autorId;
    private int categoriaId;

    public Libro(int id, String title, String editorial, int autorId, int categoriaId) {
        this.id = id;
        this.name = title;
        this.editorial = editorial;
        this.autorId = autorId;
        this.categoriaId = categoriaId;
    }

    public Libro(String name, int editorialId, int autorId, int categoriaId) {
        this.name = name;
        this.editorialId = editorialId;
        this.autorId = autorId;
        this.categoriaId = categoriaId;
    }
    public Libro(int id, String name, int editorialId, int autorId, int categoriaId) {
        this.id = id;
        this.name = name;
        this.editorialId = editorialId;
        this.autorId = autorId;
        this.categoriaId = categoriaId;
    }

    public Libro(String title, String editorial, int autorId, int categoriaId) {
        this.name = title;
        this.editorial = editorial;
        this.autorId = autorId;
        this.categoriaId = categoriaId;
    }

    public Libro() {
    }

    public Libro(String title, String editorial) {
        this.name = title;
        this.editorial = editorial;
    }

    public int getEditorialId() {
        return editorialId;
    }

    public void setEditorialId(int editorialId) {
        this.editorialId = editorialId;
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


    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public int getAutorId() {
        return autorId;
    }

    public void setAutorId(int autorId) {
        this.autorId = autorId;
    }

    public int getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(int categoriaId) {
        this.categoriaId = categoriaId;
    }
}
