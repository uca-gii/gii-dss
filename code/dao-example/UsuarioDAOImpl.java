import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación JDBC del DAO para Usuario
 * Encapsula toda la lógica de acceso a datos SQL
 */
public class UsuarioDAOImpl implements UsuarioDAO {
    
    private Connection connection;
    
    // Sentencias SQL
    private static final String INSERT = 
        "INSERT INTO usuarios (nombre, email) VALUES (?, ?)";
    private static final String SELECT_BY_ID = 
        "SELECT * FROM usuarios WHERE id = ?";
    private static final String UPDATE = 
        "UPDATE usuarios SET nombre = ?, email = ? WHERE id = ?";
    private static final String DELETE = 
        "DELETE FROM usuarios WHERE id = ?";
    private static final String SELECT_ALL = 
        "SELECT * FROM usuarios";

    public UsuarioDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Usuario usuario) {
        try (PreparedStatement stmt = connection.prepareStatement(INSERT)) {
            stmt.setString(1, usuario.getNombre());
            stmt.setString(2, usuario.getEmail());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenceException("Error creando usuario", e);
        }
    }

    @Override
    public Usuario read(int id) {
        try (PreparedStatement stmt = connection.prepareStatement(SELECT_BY_ID)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapRowToUsuario(rs);
                }
            }
        } catch (SQLException e) {
            throw new PersistenceException("Error leyendo usuario", e);
        }
        return null;
    }

    @Override
    public void update(Usuario usuario) {
        try (PreparedStatement stmt = connection.prepareStatement(UPDATE)) {
            stmt.setString(1, usuario.getNombre());
            stmt.setString(2, usuario.getEmail());
            stmt.setInt(3, usuario.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenceException("Error actualizando usuario", e);
        }
    }

    @Override
    public void delete(int id) {
        try (PreparedStatement stmt = connection.prepareStatement(DELETE)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenceException("Error eliminando usuario", e);
        }
    }

    @Override
    public List<Usuario> findAll() {
        List<Usuario> usuarios = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(SELECT_ALL);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                usuarios.add(mapRowToUsuario(rs));
            }
        } catch (SQLException e) {
            throw new PersistenceException("Error listando usuarios", e);
        }
        return usuarios;
    }

    /**
     * Helper: mapea una fila de ResultSet a un objeto Usuario
     */
    private Usuario mapRowToUsuario(ResultSet rs) throws SQLException {
        return new Usuario(
            rs.getInt("id"),
            rs.getString("nombre"),
            rs.getString("email")
        );
    }
}
