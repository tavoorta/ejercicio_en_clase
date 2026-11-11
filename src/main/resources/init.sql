create table if not exists equipo (
    Id INT PRIMARY KEY AUTO_INCREMENT,
    Nombre VARCHAR(60) not null,
    Descripcion VARCHAR(255),
    Fecha DATE,
    Estado VARCHAR(20) not null default 'PENDIENTE'
);