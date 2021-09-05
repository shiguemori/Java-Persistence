## App Java 

Utilizamos o redis em paralelo com o MySQL para aumentar o seu desempenho.
A query do banco será executada no servidor uma vez. Neste momento o Redis irá cachear a query. Após realizar a mesma pesquisa todo resultado será vindo do Redis, sem a necessidade de consultar o banco novamente.

#### Configuração

Para subir as configurações bastar executar o docker que o banco e o redis serão startados, e importar o dump-fiap para carregar a massa de dados