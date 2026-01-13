package modelo;

public class CuentaCorriente extends CuentaBancaria implements Transaccion { //EXTENDS PARA HEREDAR DE CUENTA BANCARIA//

    private int limiteRiesgo;

    public CuentaCorriente(int codigo, String titular, double saldo, int limiteRiesgo) {
        super(codigo, titular, saldo);
        this.limiteRiesgo = 250;
    }

    /**
     * SUMA AL SALDO EL DEPOSITO INTRODUCIDO, Y RETORNA TRUE SI SE HA REALIZADO
     * O FALSE SI NO
     *
     * @param double
     * @return boolean
     */
    @Override
    public boolean depositar(double deposito) {
        boolean realizado = false;
        if (deposito > MAX_DEPOSITO || deposito < 0) {
        } else {
            saldo += deposito;
            realizado = true;
        }
        return realizado;
    }

    /**
     * RETIRA EL MONTO INGRESADO, SI EL LIMITE DE RIESGO ES MAYOR QUE EL RETIRO
     *
     * @param double
     * @return boolean
     */
    @Override
    public boolean retirar(double retiro) {
        boolean realizado = false;
        if ((saldo + limiteRiesgo) >= retiro) {
            saldo -= retiro;
            realizado = true;
        }
        return realizado;
    }

    /**
     * REALIZA LA TRANSFERENCIA, ENTRE CUENTAS CORRIENTE
     * @param destino
     * @param cantidad
     * @return
     */
    @Override
    public boolean transferir(CuentaBancaria destino, double cantidad) {
        boolean realizado = false;
        if (retirar(cantidad)) { 
            destino.sumarSaldo(cantidad); 
            realizado = true;
        }

        return realizado;
    }

    public String toString() {
        return "CuentaCorriente - codigo= " + codigo + "\ntitular= " + titular + "\nsaldo= " + saldo + "\nlimiteRiesgo= " + limiteRiesgo;
    }

    /**
     *
     */
    public void consultarSaldo() {
        System.out.println("Saldo actual de " + titular + ": " + saldo);
    }

    //GETTERS//
    public int getLimiteRiesgo() {
        return limiteRiesgo;
    }

    //SETTERS//
    public void setLimiteRiesgo(int limiteRiesgo) {
        this.limiteRiesgo = limiteRiesgo;
    }

}
