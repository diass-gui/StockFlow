-- StockFlow — Script de Seed de Dados (MySQL)

-- INSERT de Empresa
INSERT INTO empresa (nome, cnpj, email) VALUES
('Tech Solutions Ltda', '12345678000199', 'contato@techsolutions.com'),
('Comercial Silva & Cia', '98765432000188', 'contato@comercialsilva.com');

-- Insert de Estoque
INSERT INTO estoque (nome, empresa_id) VALUES
('Depósito Central', 1),
('Loja Física', 1),
('Depósito Matriz', 2);

-- Insert de Produtos
INSERT INTO produto (nome, preco, categoria) VALUES
('Notebook Dell Inspiron 15', 3500.00, 'Eletrônicos'),
('Mouse Logitech M170', 59.90, 'Eletrônicos'),
('Teclado Mecânico Redragon', 249.90, 'Eletrônicos'),
('Cadeira de Escritório Ergonômica', 899.00, 'Móveis'),
('Monitor LG 24 polegadas', 999.00, 'Eletrônicos'),
('Mesa de Escritório', 650.00, 'Móveis');

-- Insert de Itens de Estoque
INSERT INTO item_estoque (estoque_id, produto_id, quantidade) VALUES
(1, 1, 15),
(1, 2, 50),
(1, 3, 30),
(2, 1, 5),
(2, 5, 10),
(3, 4, 8),
(3, 6, 12);
