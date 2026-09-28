/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

/**
 *
 * @author edwin
 */


import Modelo.Estudiante;
import Vista.VistaEstudiante;

public class ControladorEstudiante {
    private VistaEstudiante vista;
    private Estudiante[] arregloEstudiantes; // Uso de arreglos solicitado

    public ControladorEstudiante(VistaEstudiante vista) {
        this.vista = vista;
    }

    public void iniciar() {
        int cantidad = vista.solicitarCantidadEstudiantes();
        
        // Inicializamos el arreglo con el tamaño 'n' ingresado
        arregloEstudiantes = new Estudiante[cantidad];

        // Llenado del arreglo
        for (int i = 0; i < arregloEstudiantes.length; i++) {
            int codigo = vista.solicitarCodigo(i + 1);
            String nombre = vista.solicitarNombre();
            double notaDesarrollo = vista.solicitarNota("Desarrollo (Software y Hardware)");
            double notaMatematica = vista.solicitarNota("Matemática");

            // Uso del constructor para crear el objeto y guardarlo en el arreglo
            arregloEstudiantes[i] = new Estudiante(codigo, nombre, notaDesarrollo, notaMatematica);
        }

        generarReporte();
        double Limite = vista.solicitarNotaLimite();
        generarReporteFiltrado(Limite);
        double incremento = vista.solicitarIncrementoNota();
        aplicarIncremento(incremento);
        generarReporteIncremento();
    }

    private void generarReporte() {
        String reporte = "--- REPORTE FINAL DE ESTUDIANTES ---\n\n";

        for (int i = 0; i < arregloEstudiantes.length; i++) {
            Estudiante est = arregloEstudiantes[i];
            
            reporte += "Código: " + est.getCodigo() + "\n";
            reporte += "Nombre: " + est.getNombre() + "\n";
            reporte += "Nota Definitiva: " + String.format("%.2f", est.calcularDefinitiva()) + "\n";
            reporte += "Estado: " + est.obtenerEstadoAprobacion() + "\n";
            reporte += "----------------------------------------\n";
        }

        vista.mostrarMensaje(reporte);
    }
    private void generarReporteFiltrado(double Limite) {
        String reporte = "--- ESTUDIANTES CON DEFINITIVA SUPERIOR A " + Limite + " ---\n\n";
        boolean encontro = false;

        for (int i = 0; i < arregloEstudiantes.length; i++) {
            Estudiante est = arregloEstudiantes[i];
            if (est.calcularDefinitiva() > Limite) {
                reporte += "Código: " + est.getCodigo() + "\n";
                reporte += "Nombre: " + est.getNombre() + "\n";
                reporte += "Nota Definitiva: " + String.format("%.2f", est.calcularDefinitiva()) + "\n";
                reporte += "----------------------------------------\n";
                encontro = true;
            }
        }

        if (!encontro) {
            reporte += "Ningún estudiante supera la nota límite ingresada.\n";
        }

        vista.mostrarMensaje(reporte);
    }
    private void aplicarIncremento(double incremento) {
        for (int i = 0; i < arregloEstudiantes.length; i++) {
            arregloEstudiantes[i].incrementarNotaDesarrollo(incremento);
        }
    }
    private void generarReporteIncremento() {
        String reporte = "--- REPORTE FINAL LUEGO DEL INCREMENTO EN DESARROLLO ---\n\n";

        for (int i = 0; i < arregloEstudiantes.length; i++) {
            Estudiante est = arregloEstudiantes[i];
            
            reporte += "Código: " + est.getCodigo() + "\n";
            reporte += "Nombre: " + est.getNombre() + "\n";
            reporte += "Nota Definitiva: " + String.format("%.2f", est.calcularDefinitiva()) + "\n";
            reporte += "Estado: " + est.obtenerEstadoAprobacion() + "\n";
            reporte += "----------------------------------------\n";
        }

        vista.mostrarMensaje(reporte);
    }
}
