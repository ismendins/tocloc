DROP SCHEMA IF EXISTS tocloc CASCADE;
CREATE SCHEMA tocloc;
SET search_path TO tocloc;

CREATE TABLE usuarios (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    senha VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL
);

CREATE TABLE locais_esportivos (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    endereco TEXT NOT NULL,
    descricao TEXT,
    proprietario_id INT NOT NULL REFERENCES usuarios(id) ON DELETE CASCADE
);

CREATE TABLE disponibilidades (
    id SERIAL PRIMARY KEY,
    local_id INT NOT NULL REFERENCES locais_esportivos(id) ON DELETE CASCADE,
    data_hora_inicio TIMESTAMP NOT NULL,
    data_hora_fim TIMESTAMP NOT NULL,
    preco DECIMAL(10,2) NOT NULL,
    status VARCHAR(20) DEFAULT 'DISPONIVEL'
);

-- Tabela de Reservas
CREATE TABLE reservas (
    id SERIAL PRIMARY KEY,
    usuario_id INT NOT NULL REFERENCES usuarios(id) ON DELETE CASCADE,
    disponibilidade_id INT UNIQUE NOT NULL REFERENCES disponibilidades(id) ON DELETE CASCADE,
    data_hora_reserva TIMESTAMP NOT NULL DEFAULT NOW(),
    status VARCHAR(20) DEFAULT 'CONFIRMADA'
);

CREATE INDEX idx_disponibilidades_local ON disponibilidades(local_id);
CREATE INDEX idx_reservas_usuario ON reservas(usuario_id);

INSERT INTO usuarios (nome, email, senha, role) VALUES
('Proprietário Exemplo', 'proprietario@tocloc.com', 'senha123', 'PROPRIETARIO'),
('Cliente Exemplo', 'cliente@tocloc.com', 'senha456', 'CLIENTE');

INSERT INTO locais_esportivos (nome, endereco, descricao, proprietario_id) VALUES
('Quadra Poliesportiva', 'Rua dos Esportes, 100', 'Quadra coberta para múltiplos esportes', 1),
('Campo Society', 'Avenida dos Atletas, 200', 'Campo de grama sintética', 1);

INSERT INTO disponibilidades (local_id, data_hora_inicio, data_hora_fim, preco) VALUES
(1, '2024-03-10 14:00:00', '2024-03-10 16:00:00', 150.00),
(2, '2024-03-11 18:00:00', '2024-03-11 20:00:00', 250.00);

INSERT INTO reservas (usuario_id, disponibilidade_id) VALUES
(2, 1);

COMMENT ON SCHEMA tocloc IS 'Esquema principal da aplicação Tocloc';
COMMENT ON TABLE usuarios IS 'Armazena informações de usuários da plataforma';
COMMENT ON COLUMN usuarios.role IS 'Papel do usuário: PROPRIETARIO ou CLIENTE';
COMMENT ON TABLE disponibilidades IS 'Registra os horários disponíveis para reserva nos locais esportivos';