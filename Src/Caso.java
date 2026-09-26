import java.util.ArrayList;

public class Caso {
    private String nombreCaso;
    private String codigoIdentificacion;
    private String detectiveResponsable;
    private Ubicacion[] ubicaciones;
    private ArrayList<Pista> pistas;

    public Caso(String nombreCaso, String codigoIdentificacion, String detectiveResponsable) {
        this.nombreCaso = nombreCaso;
        this.codigoIdentificacion = codigoIdentificacion;
        this.detectiveResponsable = detectiveResponsable;
        this.ubicaciones = new Ubicacion[5];
        this.pistas = new ArrayList<Pista>();
    }

    public String getNombreCaso() {
        return nombreCaso;
    }

    public String getCodigoIdentificacion() {
        return codigoIdentificacion;
    }

    public String getDetectiveResponsable() {
        return detectiveResponsable;
    }

    private void validarPosicion(int posicion) {
        if (posicion < 0 || posicion >= ubicaciones.length) {
            throw new IndexOutOfBoundsException("La posicion debe estar entre 0 y 4.");
        }
    }

    public void registrarUbicacion(int posicion, Ubicacion ubicacion) {
        validarPosicion(posicion);
        if (ubicaciones[posicion] != null) {
            throw new IllegalArgumentException("La posicion ya esta ocupada.");
        }
        ubicaciones[posicion] = ubicacion;
    }

    public Ubicacion obtenerUbicacion(int posicion) {
        validarPosicion(posicion);
        return ubicaciones[posicion];
    }

    public void descartarUbicacion(int posicion) {
        validarPosicion(posicion);
        if (ubicaciones[posicion] == null) {
            throw new IllegalArgumentException("No hay una ubicacion en esa posicion.");
        }
        ubicaciones[posicion] = null;
    }

    public void mostrarUbicaciones() {
        boolean hay = false;
        for (int i = 0; i < ubicaciones.length; i++) {
            if (ubicaciones[i] != null) {
                hay = true;
                System.out.println("Posicion " + i + ": " + ubicaciones[i]);
            }
        }
        if (!hay) {
            System.out.println("No hay ubicaciones registradas.");
        }
    }

    public int cantidadUbicaciones() {
        int contador = 0;
        for (Ubicacion ubicacion : ubicaciones) {
            if (ubicacion != null) {
                contador++;
            }
        }
        return contador;
    }

    public int espaciosDisponibles() {
        return ubicaciones.length - cantidadUbicaciones();
    }

    public Ubicacion ubicacionMayorRiesgo() {
        Ubicacion mayor = null;
        for (Ubicacion ubicacion : ubicaciones) {
            if (ubicacion != null && (mayor == null || ubicacion.getNivelRiesgo() > mayor.getNivelRiesgo())) {
                mayor = ubicacion;
            }
        }
        return mayor;
    }

    public void registrarPista(Pista pista) {
        if (buscarPista(pista.getCodigo()) != null) {
            throw new IllegalArgumentException("Ya existe una pista con ese codigo.");
        }
        pistas.add(pista);
    }

    public Pista buscarPista(String codigo) {
        for (Pista pista : pistas) {
            if (pista.getCodigo().equalsIgnoreCase(codigo)) {
                return pista;
            }
        }
        return null;
    }

    public boolean eliminarPista(String codigo) {
        Pista pista = buscarPista(codigo);
        if (pista != null) {
            pistas.remove(pista);
            return true;
        }
        return false;
    }

    public void mostrarPistas() {
        if (pistas.isEmpty()) {
            System.out.println("No hay pistas registradas.");
            return;
        }
        for (Pista pista : pistas) {
            System.out.println(pista);
        }
    }

    public int cantidadPistas() {
        return pistas.size();
    }

    public Pista pistaMayorImportancia() {
        if (pistas.isEmpty()) {
            return null;
        }
        Pista mayor = pistas.get(0);
        for (Pista pista : pistas) {
            if (pista.getNivelImportancia() > mayor.getNivelImportancia()) {
                mayor = pista;
            }
        }
        return mayor;
    }

    public Pista pistaMayorConfiabilidad() {
        if (pistas.isEmpty()) {
            return null;
        }
        Pista mayor = pistas.get(0);
        for (Pista pista : pistas) {
            if (pista.getNivelConfiabilidad() > mayor.getNivelConfiabilidad()) {
                mayor = pista;
            }
        }
        return mayor;
    }

    public double promedioImportancia() {
        if (pistas.isEmpty()) {
            return 0;
        }
        int suma = 0;
        for (Pista pista : pistas) {
            suma += pista.getNivelImportancia();
        }
        return (double) suma / pistas.size();
    }
}
