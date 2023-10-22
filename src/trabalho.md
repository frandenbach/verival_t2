TRABALHO 2

A classe "ServicoEstatisticas” calcula dois tipos de estatísticas sobre um conjunto de eventos: média, mediana e desvio padrão e maior ganho de performance. Para o cálculo da média, mediana e desvio padrão pode ser usadas abordagens diferentes. O cálculo do maior ganho de performance é fixo.

Tendo os códigos em mãos, faça o que se pede:

a.     Crie drivers de teste unitário para as classes EstatisticaNomal e EstatisticaDesconsidera. É necessário o uso de dubles neste caso? Que tipo de dubles?

## R: Foi usado mock. (...) COMPLETAR

b.     Crie um driver de teste unitário para a classe ServicoEstatisticas. É necessário algum tipo de duble neste caso? Que tipos de dubles?

## R: Foi usado (...) COMPLETAR

c.      Crie um driver de teste para testar a integração da classe EstatisticaNormal com ServicoEstatisticas. É necessário algum tipo de duble neste caso? Que tipos de dubles?

## R: Foi usado (...) COMPLETAR

d.     Crie um driver de teste para testar a integração da classe EstatisticaDesconsidera com ServicoEstatisticas. É necessário algum tipo de duble neste caso? Que tipos de dubles?

## R: Foi usado (...) COMPLETAR


### Relatório do Processo
- Fizemos a questão "A" primeiro para início do trabalho, podendo se acostumar e "pegar no tranco" para poder fazer os outros mais tranquilamente.
- Criamos os drivers para testes, fizemos os cálculos manualmente para escrever exatamente o resultado esperado.
- No momento de rodar o teste foi notado uma discrepância de valores do que era esperado para "mediana".
- Ao depurar o código original fornecido para fazer este trabalho, notamos a falta de um parênteses para fazer o cálculo de dividir o resultado da soma dos dois valores do meio, caso a quantidade de elementos seja par. A divisão ocorria no segundo valor e antes da soma. Tendo isto corrigido, o teste fluiu normalmente e o resultado foi exatamente como o esperado.