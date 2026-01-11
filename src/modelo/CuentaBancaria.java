package modelo;

public abstract class CuentaBancaria  { 
    
    protected final static double MAX_DEPOSITO = 9999.00;
    protected int codigo;
    protected String titular;
    protected double saldo;

    public CuentaBancaria(int codigo, String titular, double saldo) {
        this.codigo = codigo;
        this.titular = titular;
        this.saldo = Math.max(0, saldo); //VALIDA QUE LA CUENTA BANCARIA NO SE PUEDA CREAR CON SALDO NEGATIVO//
    }

    /**
     * PROCESA LA ENTRADA DEL DEPOSITO, Y SE GUARDARAN SEGUN LAS 
     * ESPECIFICACIDONES DE CADA TIPO DE CUENTA
     * @param double 
     * @return boolean
     */
    public abstract boolean depositar(double deposito);

    
    /**
     * PROCESA LA ENTRADA DEL RETIRO, Y SE GUARDARAN SEGUN LAS 
     * ESPECIFICACIDONES DE CADA TIPO DE CUENTA
     * @param retiro 
     * @return boolean
     */
    public abstract boolean retirar(double retiro);
    
    /**
     * PROCESA EL ORIGEN Y DESTINO DE LA TRANSFERENCIA
     * @param CuentaBancaria destino LA CUENTA A LA QUE VA LA TRANSFER
     * @param double cantidad EL MONTO EL CUAL SE TRANSFIERE
     * @return boolean true si se realizo la transfer, o false si no
     */
    public abstract boolean transferir(CuentaBancaria destino, double cantidad);
    
    
    //GETTERS//
    public int getCodigo() {
        return codigo;
    }

    public String getTitular() {
        return titular;
    }

    public double getSaldo() {
        return saldo;
    }

    //SETTERS//
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    
    public String toString() {
        return "CuentaBancaria{" + "codigo=" + codigo + ", titular=" + titular + ", saldo=" + saldo + '}';
    }
    
    

}
