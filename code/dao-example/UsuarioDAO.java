import java.util.List;

/**
 * Interfaz DAO para Usuario - Define el contrato de acceso a datos
 */
public interface UsuarioDAO {
    
    /**
     * Crea un nuevo usuario en la BD
     */
    void create(Usuario usuario);
    
    /**
     * Lee un usuario dado su ID
     */
    Usuario read(int id);
    
    /**
     * Actualiza los datos de un usuario
     */
    void update(Usuario usuario);
    
    /**
     * Elimina un usuario dado su ID
     */
    void delete(int id);
    
    /**
     * Obtiene la lista de todos los usuarios
     */
    List<Usuario> findAll();
}
