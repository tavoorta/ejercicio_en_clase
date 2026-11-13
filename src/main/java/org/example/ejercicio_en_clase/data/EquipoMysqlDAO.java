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
}
