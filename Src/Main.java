import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static Caso casoActual;

    public static void main(String[] args) {
        crearNuevoCaso();
        int opcion = 0;

        do {
            mostrarMenu();
            try {
                System.out.print("Seleccione una opcion: ");
                opcion = scanner.nextInt();
                scanner.nextLine();

                switch (opcion) {
                    case 1:
                        crearNuevoCaso();
                        break;
                    case 2:
                        registrarUbicacion();
                        break;
                    case 3:
                        casoActual.mostrarUbicaciones();
                        break;
                    case 4:
                        consultarUbicacion();
                        break;
                    case 5:
                        modificarUbicacion();
                        break;
                    case 6:
                        descartarUbicacion();
                        break;
                    case 7:
                        registrarPista();
                        break;
                    case 8:
                        casoActual.mostrarPistas();
                        break;
                    case 9:
                        buscarPista();
                        break;
                    case 10:
                        modificarPista();
                        break;
                    case 11:
                        eliminarPista();
                        break;
                    case 12:
                        mostrarReporte();
                        break;
                    case 13:
                        System.out.println("Programa finalizado.");
                        break;
                    default:
                        System.out.println("Opcion no valida.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: debe ingresar un numero.");
                scanner.nextLine();
            } catch (IllegalArgumentException | IndexOutOfBoundsException e) {
                System.out.println("Error: " + e.getMessage());
            } finally {
                System.out.println("Operacion finalizada.\n");
            }
        } while (opcion != 13);

        scanner.close();
    }

    private static void mostrarMenu() {
        System.out.println("========== AGENCIA DE DETECTIVES ==========");
        System.out.println("1. Nuevo caso");
        System.out.println("2. Registrar ubicacion");
        System.out.println("3. Consultar ubicaciones");
        System.out.println("4. Consultar una ubicacion");
        System.out.println("5. Modificar ubicacion");
        System.out.println("6. Descartar ubicacion");
        System.out.println("7. Registrar pista");
        System.out.println("8. Consultar pistas");
        System.out.println("9. Buscar pista");
        System.out.println("10. Modificar pista");
        System.out.println("11. Eliminar pista");
        System.out.println("12. Mostrar reporte de investigacion");
        System.out.println("13. Salir");
    }

    private static void crearNuevoCaso() {
        System.out.println("\n--- NUEVO CASO ---");
        System.out.print("Nombre del caso: ");
        String nombre = scanner.nextLine();
        System.out.print("Codigo de identificacion: ");
        String codigo = scanner.nextLine();
        System.out.print("Detective responsable: ");
        String detective = scanner.nextLine();
        casoActual = new Caso(nombre, codigo, detective);
        System.out.println("Caso creado correctamente.\n");
    }

    private static void registrarUbicacion() {
        System.out.print("Posicion (0-4): ");
        int posicion = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Codigo: ");
        String codigo = scanner.nextLine();
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Direccion o descripcion: ");
        String direccion = scanner.nextLine();
        System.out.print("Nivel de riesgo (1-10): ");
        int riesgo = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Estado: ");
        String estado = scanner.nextLine();

        Ubicacion ubicacion = new Ubicacion(codigo, nombre, direccion, riesgo, estado);
        casoActual.registrarUbicacion(posicion, ubicacion);
        System.out.println("Ubicacion registrada.");
    }

    private static void consultarUbicacion() {
        System.out.print("Posicion (0-4): ");
        int posicion = scanner.nextInt();
        scanner.nextLine();
        Ubicacion ubicacion = casoActual.obtenerUbicacion(posicion);
        if (ubicacion == null) {
            System.out.println("La posicion esta vacia.");
        } else {
            System.out.println(ubicacion);
        }
    }

    private static void modificarUbicacion() {
        System.out.print("Posicion (0-4): ");
        int posicion = scanner.nextInt();
        scanner.nextLine();
        Ubicacion ubicacion = casoActual.obtenerUbicacion(posicion);

        if (ubicacion == null) {
            System.out.println("No hay una ubicacion en esa posicion.");
            return;
        }

        System.out.print("Nuevo nivel de riesgo (1-10): ");
        int riesgo = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Nuevo estado: ");
        String estado = scanner.nextLine();

        ubicacion.setNivelRiesgo(riesgo);
        ubicacion.setEstado(estado);
        System.out.println("Ubicacion modificada.");
    }

    private static void descartarUbicacion() {
        System.out.print("Posicion (0-4): ");
        int posicion = scanner.nextInt();
        scanner.nextLine();
        casoActual.descartarUbicacion(posicion);
        System.out.println("Ubicacion descartada.");
    }

    private static void registrarPista() {
        System.out.print("Codigo: ");
        String codigo = scanner.nextLine();
        System.out.print("Descripcion: ");
        String descripcion = scanner.nextLine();
        System.out.print("Tipo de evidencia: ");
        String tipo = scanner.nextLine();
        System.out.print("Nivel de importancia (1-10): ");
        int importancia = scanner.nextInt();
        System.out.print("Nivel de confiabilidad (0-100): ");
        int confiabilidad = scanner.nextInt();
        scanner.nextLine();

        Pista pista = new Pista(codigo, descripcion, tipo, importancia, confiabilidad);
        casoActual.registrarPista(pista);
        System.out.println("Pista registrada.");
    }

    private static void buscarPista() {
        System.out.print("Codigo de la pista: ");
        String codigo = scanner.nextLine();
        Pista pista = casoActual.buscarPista(codigo);
        if (pista == null) {
            System.out.println("Pista no encontrada.");
        } else {
            System.out.println(pista);
        }
    }

    private static void modificarPista() {
        System.out.print("Codigo de la pista: ");
        String codigo = scanner.nextLine();
        Pista pista = casoActual.buscarPista(codigo);

        if (pista == null) {
            System.out.println("Pista no encontrada.");
            return;
        }

        System.out.print("Nueva descripcion: ");
        String descripcion = scanner.nextLine();
        System.out.print("Nuevo tipo de evidencia: ");
        String tipo = scanner.nextLine();
        System.out.print("Nueva importancia (1-10): ");
        int importancia = scanner.nextInt();
        System.out.print("Nueva confiabilidad (0-100): ");
        int confiabilidad = scanner.nextInt();
        scanner.nextLine();

        pista.setDescripcion(descripcion);
        pista.setTipoEvidencia(tipo);
        pista.setNivelImportancia(importancia);
        pista.setNivelConfiabilidad(confiabilidad);
        System.out.println("Pista modificada.");
    }

    private static void eliminarPista() {
        System.out.print("Codigo de la pista: ");
        String codigo = scanner.nextLine();
        if (casoActual.eliminarPista(codigo)) {
            System.out.println("Pista eliminada.");
        } else {
            System.out.println("Pista no encontrada.");
        }
    }

    private static void mostrarReporte() {
        System.out.println("\n--- REPORTE DE INVESTIGACION ---");
        System.out.println("Caso: " + casoActual.getNombreCaso());
        System.out.println("Codigo: " + casoActual.getCodigoIdentificacion());
        System.out.println("Detective: " + casoActual.getDetectiveResponsable());
        System.out.println("Ubicaciones registradas: " + casoActual.cantidadUbicaciones());
        System.out.println("Espacios disponibles: " + casoActual.espaciosDisponibles());

        Ubicacion mayorRiesgo = casoActual.ubicacionMayorRiesgo();
        System.out.println("Ubicacion con mayor riesgo: " + (mayorRiesgo == null ? "No hay ubicaciones" : mayorRiesgo));

        System.out.println("Pistas registradas: " + casoActual.cantidadPistas());
        Pista mayorImportancia = casoActual.pistaMayorImportancia();
        Pista mayorConfiabilidad = casoActual.pistaMayorConfiabilidad();

        System.out.println("Pista con mayor importancia: " + (mayorImportancia == null ? "No hay pistas" : mayorImportancia));
        System.out.println("Pista con mayor confiabilidad: " + (mayorConfiabilidad == null ? "No hay pistas" : mayorConfiabilidad));

        if (casoActual.cantidadPistas() == 0) {
            System.out.println("Promedio de importancia: No disponible");
        } else {
            System.out.printf("Promedio de importancia: %.2f%n", casoActual.promedioImportancia());
        }
    }
}
