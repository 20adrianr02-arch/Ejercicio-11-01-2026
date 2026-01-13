package main;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import modelo.CuentaAhorro;
import modelo.CuentaBancaria;
import modelo.CuentaCorriente;

public class Ejer7_tema6_cuentasCorrientes_AR {
    
    static final Scanner teclado = new Scanner(System.in);
    static final Random aleatorio = new Random();
    
    private static final int num = 10;
    private static CuentaBancaria[] arrayCuentas = new CuentaBancaria[num];

    /**
     * ASIGNA CUENTAS AL ARRAY, Y LLENA DATOS DEPENDIENDO DEL TIPO
     *
     * @param arrayCuentas
     */
    public static void llenarArrayCuentas(CuentaBancaria[] arrayCuentas) {
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

        for (int i = 1; i < arrayCuentas.length; i++) {
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
                + "1- Alta cuenta \n"
                + "2- Listado\n"
                + "3- Retirar dinero\n"
                + "4- Ingresar dinero\n"
                + "5- Realizar transferencia\n"
                + "6- Fin \n"
                + "----------------------------------");
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
                System.out.println("----------ALTA----------");
                altaCuenta();
            }

            case 2 -> {
                eleccion = 1;
                System.out.println("----------LISTADO----------");
                for (int i = 0; i < arrayCuentas.length; i++) {
                    System.out.println(arrayCuentas[i] + "\n--------------------");
                }
            }

            case 3 -> {
                System.out.println("----------RETIRO----------");
                retiroCuenta();
                eleccion = 2;
            }

            case 4 -> {
                System.out.println("----------DEPOSITO----------");
                depositoCuenta();
                eleccion = 3;
            }

            case 5 -> {
                System.out.println("----------TRANSFERENCIA----------");
                transferencia();
                eleccion = 4;
            }

            case 6 -> {
                System.out.println("----------FINALIZADO----------");
                eleccion = 5;
            }

        }
        return eleccion;
    }

    /**
     * PIDE Y VALIDA LA ELECCION DEL USUARIO
     *
     * @return int LA OPCION DEL USUAURIO
     */
    public static int eleccion() {
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
     * PIDE EL NOMBRE DEL USUARIO, PARA DAR DE ALTA A LA CUENTA
     */
    public static String pideNombre() {
        Scanner teclado = new Scanner(System.in);
        String nombre = teclado.next();
        return nombre;
    }

    /**
     * PIDE AL USUARIO EL CODIGO Y VERIFICA QUE SEA UN VALOR VALIDO
     *
     * @return int el codigo que introduce el usu
     */
    public static int pideCodigo() {
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
     * PIDE TIPO DE CONSOLA, NOMBRE Y GENERA LOS VALORES CORRESPONDIENTES PARA
     * DAR DE ALTA UNA CUENTA, YA SEA CORRIENTE O AHORRO
     */
    public static void altaCuenta() {
        int espacio = buscaEspaciosVacios(); //BUSCA UN ESPACIO VACIO Y DEVUELVE SU POSICION //
        if (espacio!=-1) {

            int tipoCuenta = 0; //DETERMINARA EL TIPO DE CUENTA
            System.out.println("<> Introduce nombre de titular");
            String titular = pideNombre(); // SE INTRODUCE NOMBRE DEL TITULAR
            int codigo = 1; // SE GENERA CODIGO 
            double saldo = 0;
            boolean correcto = false;
            System.out.println("<> Introduce (1)-Cuenta corriente o (2)-Cuenta ahorro");
            while (!correcto) { //VALIDAR QUE SEA UN NUMERO ENTRE 1 Y 2
                try {
                    tipoCuenta = teclado.nextInt();
                    if (tipoCuenta >= 1 && tipoCuenta <= 2) {
                        correcto = true;
                    } else {
                        System.out.println("<> Introduce una opcion valida (1-Corriente, 2-Ahorro");
                    }
                } catch (InputMismatchException exc) {
                    teclado.nextLine(); //LIMPIA Y PIDE //
                    System.out.println("<> Introduce un valor valido (1-2)");
                }
            }
            // CREAR UNA CUENTA AHORRO O UNA CUENTA CORRIENTE
            if (tipoCuenta == 1) {  //CORRIENTE//
                int limiteRiesgo = 250;
                arrayCuentas[espacio] = new CuentaCorriente(codigo, titular, saldo, limiteRiesgo);
                System.out.println("<> Cuenta corriente, creada con exito ");
            } else { //AHORRO//
                int numMeses = 0;
                double tasaIntereses = 5;
                arrayCuentas[espacio] = new CuentaAhorro(codigo, titular, saldo, numMeses, tasaIntereses);
                System.out.println("<> Cuenta ahorro, creada con exito ");
            }
        }else {
            System.out.println("Limite de cuentas activas alcanzado");
        }

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
     * BUSCA ESPACIOS VACIOS, SI ESTA VACIO DEVUELVE SU POSICION EN EL ARRAY
     * Y SI NO HAY NINGUN HUECO LIBRE DEVUELVE -1
     * @return int UN UNO NEGATIVO
     */
    public static int buscaEspaciosVacios() {
        int espacio=-1;
        for (int i = 0; i < 10; i++) {
            if (arrayCuentas[i] == null) {
                espacio = i;
            }
        }
        return espacio;
    }

    /**
     * BUSCA EL CODIGO DE LA CUENTA Y REALIZA EL RETIRO
     *
     * @param codigo
     */
    public static void retiroCuenta() {

        System.out.print("<> Introduce el codigo de la cuenta : ");
        CuentaBancaria cuenta = buscarCuenta(pideCodigo());

        if (cuenta != null) {
            System.out.println("<> Cuenta encontrada: " + cuenta.getTitular());
            System.out.print("<> Monto a retirar: ");
            if (cuenta.retirar(pideMonto())) {
                System.out.println("<> Retiro exitoso. Saldo actual: " + cuenta.getSaldo());
            } else {
                System.out.println("<> No se pudo realizar el retiro.");
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
    public static void depositoCuenta() {
        System.out.print("<> Introduce el codigo de la cuenta: ");
        CuentaBancaria cuenta = buscarCuenta(pideCodigo());

        if (cuenta != null) {
            System.out.println("<> Cuenta encontrada: " + cuenta.getTitular());
            System.out.print("<> Monto a depositar: ");
            if (cuenta.depositar(pideMonto())) {
                System.out.println("<> Deposito exitoso. Saldo actual: " + cuenta.getSaldo());
            } else {
                System.out.println("<> No se pudo realizar el deposito.");
            }
        } else {
            System.out.println("<> Error: Cuenta no encontrada.");
        }
    }

    /**
     * REALIZA LA TRASNFERENCIA ENTRE CUENTAS, LAS UBICA POR CODIGO Y LUEGO
     * REALIZA LA TRASNFER
     */
    public static void transferencia() {
        System.out.print("<> Codigo cuenta ORIGEN: ");
        CuentaBancaria origen = buscarCuenta(pideCodigo()); //BUSCA LA CUENTA DE ORIGEN Y LA ALMACENA

        System.out.print("<> Codigo cuenta DESTINO: ");
        CuentaBancaria destino = buscarCuenta(pideCodigo()); //BUSCA LA CUENTA DE DESTINO

        if (origen != null && destino != null) {
            System.out.print("<> Monto a transferir: ");
            if (origen.transferir(destino, pideMonto())) {
                System.out.println("<> Transferencia realizada con exito.");
            } else {
                System.out.println("<> La transferencia ha sido rechazada.");
            }
        } else {
            System.out.println("<> Error: Una o ambas cuentas no existen.");
        }
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
