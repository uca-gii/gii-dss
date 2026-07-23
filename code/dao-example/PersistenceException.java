/**
 * Excepción de persistencia - wrapper de SQLException
 */
public class PersistenceException extends RuntimeException {
    
    public PersistenceException(String mensaje) {
        super(mensaje);
    }
    
    public PersistenceException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
