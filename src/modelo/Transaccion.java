package modelo;

public interface Transaccion {
    
    /**
     * Transfiere una cantidad a otra cuenta destino.
     * @param destino La cuenta que recibe el dinero.
     * @param cantidad Cuánto dinero mover.
     * @return true si la transferencia fue exitosa.
     */
    public boolean transferir(CuentaBancaria destino, double cantidad);
    
    public void consultarSaldo();
    
}
