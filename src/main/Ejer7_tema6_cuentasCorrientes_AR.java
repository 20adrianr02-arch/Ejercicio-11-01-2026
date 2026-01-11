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
                }else System.out.println("<> Introduce un codigo valido 1-10");
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
                }else{
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
     * BUSCA EL CODIGO DE LA CUENTA Y REALIZA EL RETIRO
     *
     * @param codigo
     */
    public static void retiroCuenta(int codigo) {
        for (int i = 0; i < arrayCuentas.length; i++) {
            if (arrayCuentas[i].getCodigo() == codigo) {
                System.out.println("<> Cuenta encontrada");
                System.out.print("<> Monto a retirar : ");
                arrayCuentas[i].retirar(pideMonto());
                System.out.println("Retiro exitoso, saldo actual : " + arrayCuentas[i].getSaldo());
            }
        }

    }

    /**
     * BUSCA EL CODIGO DE LA CUENTA Y REALIZA EL DEPOSITO
     *
     * @param codigo
     */
    public static void depositoCuenta(int codigo) {
        for (int i = 0; i < arrayCuentas.length; i++) {
            if (arrayCuentas[i].getCodigo() == codigo) {
                System.out.println("<> Cuenta encontrada");
                System.out.print("<> Monto a ingresar : ");
                arrayCuentas[i].depositar(pideMonto());
                System.out.println("Ingreso exitoso, saldo actual : " + arrayCuentas[i].getSaldo());
            }
        }

    }

    public static void transferencia() {
        int codigoOrigen= 0;
        int codigoDestino = 0;
        codigoOrigen= pideCodigo();
        for (int i = 0; i < arrayCuentas.length; i++) { //FOR 1 PARA CUENTA DE ORIGEN
            if (codigoOrigen == arrayCuentas[i].getCodigo()) {//SI EL CODIGO INTRODUCIDO POR EL USU, ES IGUAL AL CODIGO DE LA CUENTA
                System.out.println("<> Cuenta Encontrada");
                System.out.print("<> Introduce el codigo de la cuenta de destino : ");
                codigoDestino = pideCodigo();
                for (int j = 0; j < arrayCuentas.length; j++) { //FOR 2 PARA CUENTA DE DESTINO //BUSCA LA CUENTA DE DESTINO MEDIANTE EL CODIGO
                    if (codigoDestino == arrayCuentas[j].getCodigo()) {
                        System.out.println("<> Cuenta de destino encontrada");
                        System.out.print("<> Introduce el  monto de la transferencia : ");//PIDE EL MONTO A TRAMSFERIR
                        if(arrayCuentas[i].transferir(arrayCuentas[j], pideMonto()) == true){ //RALIZA LA TRAMSFERENCIA CON LA CUENTA DE ORIGEN Y CUENTA DE DESTINO
                            System.out.println("Transferencia realizada");
                        }else {
                            System.out.println("No se ha podido realizar la transferencia ");
                        }
                    }
                }
                System.out.println("Transferencia realizada con exito, saldo actual :  " + arrayCuentas[i].getSaldo() );
            }
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
                retiroCuenta(pideCodigo());
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
