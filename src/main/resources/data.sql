DELETE FROM tutoria;
DELETE FROM curso;
DELETE FROM orientacion;

INSERT INTO orientacion (nombre, email, codigocentro, nombrecentro, eliminado) VALUES
('Juan Pérez', 'juan.perez', '12346677', 'Colegio ABC',false),
('María Gómez', 'maria.gomez', '12346677', 'Instituto XYZ',false),
('Carlos Ramírez', 'carlos.ramirez', '12346677', 'Escuela Primaria 123',false),
('Ana Torres', 'ana.torres', '12346677', 'Colegio Nacional',false),
('Pedro Martínez', 'pedro.martinez', '12346677', 'Instituto Técnico Superior',false);