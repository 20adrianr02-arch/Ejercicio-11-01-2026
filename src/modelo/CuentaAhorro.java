package modelo;

public class CuentaAhorro extends CuentaBancaria implements Transaccion { //implements Transaccion INCLUYE LA INTERFAZ

    private int numMeses;
    private double tasaIntereses;

    public CuentaAhorro(int codigo, String titular, double saldo, int numMeses, double tasaIntereses) {
        super(codigo, titular, saldo);
        this.numMeses = numMeses;
        this.tasaIntereses = tasaIntereses;
    }

    /**
     * SUMA LA CANTIDAD DEL DEPOSITO, SI numMeses ES CERO, AL REALIZAR EL
     * DEPOSITO numMeses PASA A 12
     *
     * @param double
     * @return boolean
     */
    @Override
    public boolean depositar(double deposito) {
        boolean realizado = false;
        if (deposito > 0 && numMeses == 0) {
            numMeses = 12;
            saldo += deposito;
            realizado = true;
        }
        return realizado;
    }

    /**
     * RETIRA EL MONTO INGRESADO, SI EL SALDO ES MAYOR QUE EL RETIRO, Y SU
     * numMeses ES IGUAL A CERO
     *
     * @param double
     * @return boolean
     */
    @Override
    public boolean retirar(double retiro) {
        boolean realizado = false;
        if (saldo >= retiro && numMeses == 0) {
            saldo -= retiro;
            realizado = true;
        }
        return realizado;
    }

    /**
     * REALIZA TRANSFERENCIA ENTRE CUENTAS DE AHORRO
     * @param destino
     * @param cantidad
     * @return
     */
    @Override
    public boolean transferir(CuentaBancaria destino, double cantidad) {
        boolean realizado = false;
        if (destino instanceof CuentaAhorro && saldo >= cantidad) {
            saldo -= cantidad; 
            ((CuentaAhorro) destino).sumarSaldo(cantidad);//CASTEO DE CUENTAAHORRO, PARA INFORMALE A JAVA QUE DESTINO ES UNA CUENTA DE AHORRO
            realizado = true;
            System.out.println("<> Transferencia interna Ahorro-Ahorro ejecutada.");
        }

        return realizado;
    }

    public String toString() {
        return "CuentaAhorro - codigo= " + codigo + "\ntitular= " + titular + "\nsaldo= " + saldo + "\nnumMeses= " + numMeses + "\ntasaIntereses= " + tasaIntereses;
    }

    /**
     *
     */
    public void consultarSaldo() {
        System.out.println("Saldo actual de " + titular + ": " + saldo);
    }

    //GETTERS//
    public int getNumMeses() {
        return numMeses;
    }

    public double getTasaIntereses() {
        return tasaIntereses;
    }

    //SETTERS//
    public void setNumMeses(int numMeses) {
        this.numMeses = numMeses;
    }

    public void setTasaIntereses(double tasaIntereses) {
        this.tasaIntereses = tasaIntereses;
    }

}
