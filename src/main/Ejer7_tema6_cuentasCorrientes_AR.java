package main;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import modelo.CuentaAhorro;
import modelo.CuentaBancaria;
import modelo.CuentaCorriente;

public class Ejer7_tema6_cuentasCorrientes_AR {

    private static final int num = 10;
    private static CuentaBancaria[] arrayCuentas = new CuentaBancaria[num];

    /**
     * ASIGNA CUENTAS AL ARRAY, Y LLENA DATOS DEPENDIENDO DEL TIPO
     *
     * @param arrayCuentas
     */
    public static void llenarArrayCuentas(CuentaBancaria[] arrayCuentas) {
        Random aleatorio = new Random();
        int tipoCuenta = 0;
        int codigo = 0;
        String titular = "";
        double saldo = 0;

        String[] nombres = {
            "Roboute Guilliman",
            "Leman Russ",
            "Rogal Dorn",
            "Magnus el Rojo",
            "Mortarion",
            "Fulgrim",
            "Perturabo",
            "Lorgar Aurelian",
            "Corvus Corax",
            "Ferrus Manus",
            "Vulkan",
            "Konrad Curze",
            "Angron",
            "Jagatai Khan",
            "Alpharius Omegon"
        };

        for (int i = 0; i < arrayCuentas.length; i++) {
            tipoCuenta = aleatorio.nextInt(1, 3);
            codigo++;
            titular = nombres[aleatorio.nextInt(0, 15)];
            saldo = (double) aleatorio.nextInt(0, 10000);
            if (tipoCuenta == 1) {
                int limiteRiesgo = 250;
                arrayCuentas[i] = new CuentaCorriente(codigo, titular, saldo, limiteRiesgo);
            } else {
                int numMeses = 0;
                double tasaIntereses = 5;
                arrayCuentas[i] = new CuentaAhorro(codigo, titular, saldo, numMeses, tasaIntereses);
            }

        }

    }

    /**
     * MUESTRA EL MENU, DE LOS DIVERSAS OPCIONES
     */
    public static void menu() {
        System.out.println("---------------MENU---------------\n"
                + "1- Listado\n"
                + "2- Retirar dinero\n"
                + "3- Ingresar dinero\n"
                + "4- Realizar transferencia\n"
                + "5- Fin \n"
                + "----------------------------------");
    }

    /**
     * PIDE Y VALIDA LA ELECCION DEL USUARIO
     *
     * @return int LA OPCION DEL USUAURIO
     */
    public static int eleccion() {
        Scanner teclado = new Scanner(System.in);
        boolean correcto = false;
        int opcion = 0;
        while (!correcto) {
            try {
                opcion = teclado.nextInt();
                if (opcion == 1 || opcion == 2 || opcion == 3 || opcion == 4 || opcion == 5) {
                    correcto = true;
                } else {
                    System.out.println("<> Introduce una opcion validad 1-5");
                }
            } catch (InputMismatchException exc) {
                teclado.nextLine(); //LIMPIA Y PIDE //
                System.out.println("<> Introduce una opcion validad");
            }

        }
        return opcion;
    }

    /**
     * PIDE AL USUARIO EL CODIGO Y VERIFICA QUE SEA UN VALOR VALIDO
     *
     * @return int el codigo que introduce el usu
     */
    public static int pideCodigo() {
        Scanner teclado = new Scanner(System.in);
        boolean correcto = false;
        int codigo = 0;
        while (!correcto) {
            try { // SI HAY UN FALLO EN EL SIGUIENTE CODIGO, NO PRODUCE ERROR, SI NO QUE EJECUTA EL catch
                codigo = teclado.nextInt();
                if (codigo > 0 && codigo < 11) { //SI ES MAYOR QUE O Y MENOR QUE 10
                    correcto = true;
                } else {
                    System.out.println("<> Introduce un codigo valido 1-10");
                }
            } catch (InputMismatchException exc) {
                teclado.nextLine(); //LIMPIA Y PIDE //
                System.out.println("<> Introduce un codigo valido");
            }

        }
        return codigo;
    }

    /**
     * PIDE AL USUARIO EL MONTO Y VERIFICA QUE SEA UN VALOR VALIDO
     *
     * @return int el codigo que introduce el usu
     */
    public static double pideMonto() {
        Scanner teclado = new Scanner(System.in);
        boolean correcto = false;
        double monto = 0;
        while (!correcto) {
            try {
                monto = teclado.nextInt();
                if (monto > 0 && monto < 9999) {
                    correcto = true;
                } else {
                    System.out.println("Introduce un monto valido 0-9999");
                }
            } catch (InputMismatchException exc) {
                teclado.nextLine(); //LIMPIA Y PIDE //
                monto = 0;
                System.out.println("<> Introduce un monto valido");
            }

        }
        return monto;
    }

    /**
     * BUSCA CUENTA EN EL ARRAY MEDIENTE SU CODIGO Y DEVUELVE LA CUENTA
     *
     * @param codigo EL CODIGO A BUSCAR
     * @return EL OBJETO DE CUENTA BANCARIA
     */
    public static CuentaBancaria buscarCuenta(int codigo) {
        for (int i = 0; i < arrayCuentas.length; i++) {
            if (arrayCuentas[i] != null && arrayCuentas[i].getCodigo() == codigo) { //BUSCA QUE SEA DIFERENTE A NULL Y QUE EL CODIGO SEA IGUAL
                return arrayCuentas[i];
            }
        }
        return null;
    }

    /**
     * BUSCA EL CODIGO DE LA CUENTA Y REALIZA EL RETIRO
     *
     * @param codigo
     */
    public static void retiroCuenta() {
        System.out.print("<> Introduce el código de la cuenta: ");
        CuentaBancaria cuenta = buscarCuenta(pideCodigo());

        if (cuenta != null) {
            System.out.println("<> Cuenta encontrada: " + cuenta.getTitular());
            System.out.print("<> Monto a retirar: ");
            if (cuenta.retirar(pideMonto())) {
                System.out.println("Retiro exitoso. Saldo actual: " + cuenta.getSaldo());
            } else {
                System.out.println("No se pudo realizar el retiro.");
            }
        } else {
            System.out.println("<> Error: Cuenta no encontrada.");
        }
    }

    /**
     * REALIZA EL DEPOSITO A LA CUENTA SOLICITADA POR SU CODIGO
     *
     * @param int CODIGO INTRODUCIDO POR EL USU
     */
    public static void depositoCuenta(int codigo) {
        System.out.print("<> Introduce el código de la cuenta: ");
        CuentaBancaria cuenta = buscarCuenta(pideCodigo());
        
          if (cuenta != null) {
            System.out.println("<> Cuenta encontrada: " + cuenta.getTitular());
            System.out.print("<> Monto a depositar: ");
            if (cuenta.depositar(pideMonto())) {
                System.out.println("deposito exitoso. Saldo actual: " + cuenta.getSaldo());
            } else {
                System.out.println("No se pudo realizar el deposito.");
            }
        } else {
            System.out.println("<> Error: Cuenta no encontrada.");
        }
    }

    
    /**
     * REALIZA LA TRASNFERENCIA ENTRE CUENTAS, LAS UBICA POR CODIGO Y LUEGO REALIZA LA TRASNFER
     */
    public static void transferencia() {
        System.out.print("<> Código cuenta ORIGEN: ");
        CuentaBancaria origen = buscarCuenta(pideCodigo()); //BUSCA LA CUENTA DE ORIGEN Y LA ALMACENA

        System.out.print("<> Código cuenta DESTINO: ");
        CuentaBancaria destino = buscarCuenta(pideCodigo()); //BUSCA LA CUENTA DE DESTINO

        if (origen != null && destino != null) {
            System.out.print("<> Monto a transferir: ");
            if (origen.transferir(destino, pideMonto())) {
                System.out.println("Transferencia realizada con éxito.");
            } else {
                System.out.println("La transferencia ha sido rechazada por el sistema.");
            }
        } else {
            System.out.println("<> Error: Una o ambas cuentas no existen.");
        }
    }

    /**
     * EJECUTA LA OPCION DEL MENU, MEDIANTE LA ELECCION DEL USU, Y DEVUELVE LA
     * ELECCION EN INT
     *
     * @param int OPCION ELEGIDA POR EL USU
     * @return int OPCION que introduce el usu
     */
    public static int opcionMenu(int opcion) {
        int eleccion = 0;
        switch (opcion) {

            case 1 -> {
                eleccion = 1;
                System.out.println("----------LISTADO----------");
                for (int i = 0; i < arrayCuentas.length; i++) {
                    System.out.println(arrayCuentas[i] + "\n--------------------");
                }
            }

            case 2 -> {
                System.out.println("----------RETIRO----------");
                System.out.print("<> Introduce el codigo de la cuenta : ");
                retiroCuenta();
                eleccion = 2;
            }

            case 3 -> {
                System.out.println("----------INGRESO----------");
                System.out.print("<> Introduce el codigo de la cuenta : ");
                depositoCuenta(pideCodigo());
                eleccion = 3;
            }

            case 4 -> {
                System.out.println("----------TRANSFERENCIA----------");
                System.out.print("<> Codigo de cuenta de origen : ");
                transferencia();
                eleccion = 4;
            }

            case 5 -> {
                System.out.println("----------FINALIZADO----------");
                eleccion = 5;
            }

        }
        return eleccion;
    }

    public static void main(String[] args) {

        llenarArrayCuentas(arrayCuentas); //CREA Y LLENA EL ARRAY//
        int salir = 0;
        do { // SE REPETIRA HASTA QUE EL USURIO QUIERA SALIR (5)
            menu(); //MUESTRA EL MENU//
            salir = opcionMenu(eleccion()); //PIDE LA ELECCION POR TECLADO Y LUEGO EJECUTA LA OPCION, SI LA OPCION ES 5, SALE DEL PROGRAMA

        } while (salir != 5);

    }

}
