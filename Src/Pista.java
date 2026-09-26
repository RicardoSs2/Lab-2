public class Pista {
    private String codigo;
    private String descripcion;
    private String tipoEvidencia;
    private int nivelImportancia;
    private int nivelConfiabilidad;

    public Pista(String codigo, String descripcion, String tipoEvidencia, int nivelImportancia, int nivelConfiabilidad) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.tipoEvidencia = tipoEvidencia;
        setNivelImportancia(nivelImportancia);
        setNivelConfiabilidad(nivelConfiabilidad);
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getTipoEvidencia() {
        return tipoEvidencia;
    }

    public int getNivelImportancia() {
        return nivelImportancia;
    }

    public int getNivelConfiabilidad() {
        return nivelConfiabilidad;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setTipoEvidencia(String tipoEvidencia) {
        this.tipoEvidencia = tipoEvidencia;
    }

    public void setNivelImportancia(int nivelImportancia) {
        if (nivelImportancia < 1 || nivelImportancia > 10) {
            throw new IllegalArgumentException("La importancia debe estar entre 1 y 10.");
        }
        this.nivelImportancia = nivelImportancia;
    }

    public void setNivelConfiabilidad(int nivelConfiabilidad) {
        if (nivelConfiabilidad < 0 || nivelConfiabilidad > 100) {
            throw new IllegalArgumentException("La confiabilidad debe estar entre 0 y 100.");
        }
        this.nivelConfiabilidad = nivelConfiabilidad;
    }

    @Override
    public String toString() {
        return "Codigo: " + codigo +
                " | Descripcion: " + descripcion +
                " | Tipo: " + tipoEvidencia +
                " | Importancia: " + nivelImportancia +
                " | Confiabilidad: " + nivelConfiabilidad + "%";
    }
}
