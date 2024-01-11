CREATE TABLE produtos(
  id bigint unsigned NOT NULL AUTO_INCREMENT,
  nome varchar(100) NOT NULL,
  codigo INTEGER NOT NULL,
  status VARCHAR(50) NOT NULL,
  quantidade INTEGER NOT NULL,
  descricao VARCHAR(200) NOT NULL,
  imagem VARCHAR(200) NOT NULL,
  categoria_id bigint unsigned NOT NULL,
  ativo BOOLEAN,

  PRIMARY KEY(id),
  FOREIGN KEY(categoria_id) REFERENCES categorias(id)
);
