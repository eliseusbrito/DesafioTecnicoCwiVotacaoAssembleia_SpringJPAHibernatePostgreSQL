# Solução Back-end para Gerenciar Sessões de Votação em Assembleias

https://votacao-assembleia.herokuapp.com/pauta

No cooperativismo, cada associado possui um voto e as decisões são tomadas em assembleias, por votação. Esta solução back-end foi criada para gerenciar essas sessões de votação.
Sendo executada na nuvem, promove as seguintes funcionalidades através de uma API REST:
● Cadastrar uma nova pauta;
● Abrir uma sessão de votação em uma pauta (a sessão de votação deve ficar aberta por um tempo determinado na chamada de abertura ou 1 minuto por default);
● Receber votos dos associados em pautas (os votos são apenas 'Sim'/'Não'. Cada associado é identificado por um id único e pode votar apenas uma vez por pauta);
● Contabilizar os votos e dar o resultado da votação na pauta.
As pautas e os votos são persistidos e não são perdidos com o restart da aplicação.
Para fins de exercício, a segurança das interfaces foi abstraída e qualquer chamada para as interfaces é considerada como autorizada. A linguagem escolhida foi Java, e utiliza ferramentas como o Spring, Rest, JPA, Hibernate, H2, PostgreSQL, Heroku, Docker, RabbitMQ,Json e Postman.

A solução também foi integrada com um sistema externo que verifica a partir do CPF do associado, se ele pode votar.
O resultado da votação pode ser informado para o restante da plataforma, isto é feito através de mensageria. A solução possui um Producer que pode ser acessado via end point através de comando Get. Quando a sessão de votação fechar o usuário pode postar uma mensagem com o resultado da votação, por exemplo, via Postman.

Continuação deste documento está nos links abaixo deste diretório:

https://github.com/eliseusbrito/DesafioTecnicoCwiVotacaoAssembleia_SpringJPAHibernatePostgreSQL/blob/master/LEIAME.pdf
https://github.com/eliseusbrito/DesafioTecnicoCwiVotacaoAssembleia_SpringJPAHibernatePostgreSQL/blob/master/LEIAME.docx


