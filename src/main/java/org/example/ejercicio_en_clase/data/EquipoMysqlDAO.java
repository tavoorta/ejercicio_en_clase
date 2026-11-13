package org.example.ejercicio_en_clase.data;

import org.example.ejercicio_en_clase.models.Equipo;
import org.example.ejercicio_en_clase.models.Estado;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EquipoMysqlDAO implements DAO<Equipo> {

    private DataSource ds;

    public EquipoMysqlDAO(DataSource ds){
        this.ds = ds;
    }

    private static String sqlSelect = "SELECT * FROM equipo";
    private static String sqlInsert = "INSERT INTO equipo (Nombre, Descripcion, Fecha, Estado) VALUES (?, ?, ?, ?)";
    private static String sqlUpdate = "UPDATE equipo SET Nombre = ?, Descripcion = ?, Fecha = ?, Estado = ? WHERE Id = ?";
    private static String sqlDelete = "DELETE FROM equipo WHERE Id = ?";

    @Override
    public List<Equipo> findAll() {

        List<Equipo> salida = new ArrayList<>();

        try (Connection conn = ds.getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlSelect);
             ResultSet rs = ps.executeQuery() )
        {
            while (rs.next()) {
                salida.add(mapper(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return salida;
    }

    @Override
    public Equipo save(Equipo entity) {
        try (Connection conn = ds.getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, entity.getNombre());
            ps.setString(2, entity.getDescripcion());
            ps.setDate(3, entity.getFecha() != null ? Date.valueOf(entity.getFecha()) : null);
            ps.setString(4, entity.getEstado() != null ? entity.getEstado().name() : null);

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    entity.setId(rs.getLong(1));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return entity;
    }

    @Override
    public void update(Equipo entity) {
        try (Connection conn = ds.getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlUpdate)) {

            ps.setString(1, entity.getNombre());
            ps.setString(2, entity.getDescripcion());
            ps.setDate(3, entity.getFecha() != null ? Date.valueOf(entity.getFecha()) : null);
            ps.setString(4, entity.getEstado() != null ? entity.getEstado().name() : null);
            ps.setLong(5, entity.getId());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(Long id) {
        try (Connection conn = ds.getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlDelete)) {

            ps.setLong(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //region Method Mapper
    private static Equipo mapper(ResultSet rs) throws SQLException {
        Equipo equipo = new Equipo();
        equipo.setId(rs.getLong("Id"));
        equipo.setNombre(rs.getString("Nombre"));
        equipo.setDescripcion(rs.getString("Descripcion"));
        equipo.setFecha(rs.getDate("Fecha") != null ? rs.getDate("Fecha").toLocalDate() : null);
        equipo.setEstado(rs.getString("Estado") != null ? Estado.valueOf(rs.getString("Estado")) : Estado.PENDIENTE);
        return equipo;
    }
    //endregion Method Mapper

/*
    IMPLEMENTACION DEL CODIGO ANTERIOR, SE AGREGA SOLO PARA TENERLA EN MI REPOSITORIO Y NO PERDERLA


    private static String sqlFindById = "SELECT * FROM equipo WHERE id = ?";
    private static String sqlFindByNombre = "SELECT * FROM equipo WHERE nombre like ?";
    private static String sqlFindByEstado = "SELECT * FROM equipo WHERE disponible = ?";

    @Override
    public Optional<Equipo> findById(Long id) {
        try (Connection conn = ds.getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlFindById)) {
            ps.setLong(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.ofNullable(mapper(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    public List<Equipo> findByNombre(String nombre) {
        List<Equipo> salida = new ArrayList<>();
        try (Connection conn = ds.getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlFindByNombre)) {
            ps.setString(1, "%" + nombre + "%");

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    salida.add(mapper(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return salida;
    }


    public List<Equipo> findByEstado(Boolean status) {
        if (status == null) {
            return List.of();
        }
        List<Equipo> salida = new ArrayList<>();
        try (Connection conn = ds.getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlFindByEstado)) {
            ps.setBoolean(1, status);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    salida.add(mapper(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return salida;
    }

*/


}
