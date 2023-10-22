TRABALHO 2

A classe "ServicoEstatisticas” calcula dois tipos de estatísticas sobre um conjunto de eventos: média, mediana e desvio padrão e maior ganho de performance. Para o cálculo da média, mediana e desvio padrão pode ser usadas abordagens diferentes. O cálculo do maior ganho de performance é fixo.

Tendo os códigos em mãos, faça o que se pede:

a.     Crie drivers de teste unitário para as classes EstatisticaNomal e EstatisticaDesconsidera. É necessário o uso de dubles neste caso? Que tipo de dubles?

## R: Foram necessários o uso de dublês para facilitar a testagem. Usamos mocks para os drivers EstatisticaNormalTest e EstatisticaDesconsideraTest como uma simulação do IEventoRepository, podendo controlar as respostas que o "mock" nos dará quando seus métodos são chamados.

b.     Crie um driver de teste unitário para a classe ServicoEstatisticas. É necessário algum tipo de duble neste caso? Que tipos de dubles?

## R: Vimos a necessidade de usar mocks para isolar a classe sendo testada (no caso, ServicoEstatistica) de suas dependências IEventoRepository e ICalculoEstatistica, assim focando no comportamento da ServicoEstatistica.

c.      Crie um driver de teste para testar a integração da classe EstatisticaNormal com ServicoEstatisticas. É necessário algum tipo de duble neste caso? Que tipos de dubles?

## R: Foi usado mock. COMPLETAR

d.     Crie um driver de teste para testar a integração da classe EstatisticaDesconsidera com ServicoEstatisticas. É necessário algum tipo de duble neste caso? Que tipos de dubles?

## R: Foi usado mock. COMPLETAR


### Relatório do Processo

> Questão A
- Fizemos a questão "A" primeiro para início do trabalho, podendo se acostumar e "pegar no tranco" para poder fazer os outros mais tranquilamente.
- Criamos os drivers para testes, fizemos os cálculos manualmente para escrever exatamente o resultado esperado.
- No momento de rodar o teste foi notado uma discrepância de valores do que era esperado para "mediana".
- Ao depurar o código original fornecido para fazer este trabalho, notamos a falta de um parênteses para fazer o cálculo de dividir o resultado da soma dos dois valores do meio, caso a quantidade de elementos seja par. A divisão ocorria no segundo valor e antes da soma. Tendo isto corrigido, o teste fluiu normalmente e o resultado foi exatamente como o esperado.
- Foi mudado de lugar a remoção do primeiro e último elementos da lista de eventos para antes da criação da lista de valores. Como no caso do nosso teste usamos 5 eventos, depois da remoção, haviam apenas 3 elementos, colocando então os 3 valores na lista de valores, de acordo com o que foi dado de entrada, posteriormente ordenando-os. Foi feito o teste, e tudo aconteceu de acordo.

> Questão B
- Criamos o driver de ServicoEstatisticaTest, fazendo os cálculos manualmente para certificar que o retorno é exato ao esperado.
- O primeiro Test foi simples, uma vez que apenas foi testava o calculaEstatistica. Então mockamos o que se precisava para poder testar com sucesso.
- O segundo já foi complicado. Criamos a lista de eventos de exemplo para testar. Ao fazer o calculaAumentoPerformance, foi visto que o argumento pedia distância, o qual não tem necessidade no código, uma vez que o filtro é apenas por "ano" e o cálculo é a partir do tempo, logo, ela não é usada, então escolhemos remover.
- Foram feitos os testes e notamos mais um problema que era a diferença negativa ele não considerava. Então adicionamos um Math.abs() para neutralizar o valor.
- Executamos mais uma vez e deu erro com o valor de saída que estava retornando um valor bem abaixo que não era o esperado. O esperado do código é ele retornar qual foi a maior diferença entre os elementos da lista filtrada de eventos, mas o código estava apenas fazendo a diferença de um valor e do próximo e não verificava todos para obeter a maior. Então fizemos um segundo loop (com dois "indiceMaiorDif" para executar propriamente) para garantir que todas as diferenças entre todos os valores fossem feitas e comparadas para garantir o maior.
- Ao final do teste, o resultado foi como o esperado.