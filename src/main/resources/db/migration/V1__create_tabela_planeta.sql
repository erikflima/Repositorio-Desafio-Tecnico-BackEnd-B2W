-- Script para FlyWay executar.
-- ------------------------------------------------------

-- Criacao da tabela `planeta`.
CREATE TABLE `planeta` (
	
  `id`                             bigint       NOT NULL AUTO_INCREMENT,
  `nome`                           varchar(255) NOT NULL,
  `clima`                          varchar(255) NOT NULL,
  `terreno`                        varchar(255) NOT NULL,
  `quantidade_aparicoes_em_filmes` integer      NOT NULL,

  PRIMARY KEY (`id`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8;