CREATE DATABASE controleprojetos;
USE controleprojetos;

CREATE TABLE Responsavel (
    id_responsavel INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE Projeto (
    id_projeto INT AUTO_INCREMENT PRIMARY KEY,
    nome_projeto VARCHAR(150) NOT NULL,
    data_inicio DATE,
    id_responsavel INT NOT NULL,
	FOREIGN KEY (id_responsavel) REFERENCES Responsavel(id_responsavel) ON DELETE RESTRICT 
);

CREATE TABLE Tarefa (
    id_tarefa INT AUTO_INCREMENT PRIMARY KEY,
    descricao VARCHAR(255) NOT NULL,
    status_tarefa VARCHAR(50),
    
    id_projeto INT NOT NULL,
    FOREIGN KEY (id_projeto) 
        REFERENCES Projeto(id_projeto)
        ON DELETE CASCADE 
);


