# Sequência de Primos em Pi

O programa encontra a maior sequência contígua de casas decimais de π que pode ser
particionada inteiramente em números primos de até 4 algarismos. A sequência deve ser
formada por dígitos adjacentes de π, sem saltos, e cada grupo de dígitos dentro dela
deve ser um número primo válido.

**Exemplo** com os primeiros 20 dígitos de π (`14159265358979323846`):

```
41 | 59 | 2 | 653 | 5 | 89 | 79 | 3 | 2 | 3
```

Resultando na sequência contígua `4159265358979323` com 16 dígitos.

# Como funciona

1. O programa recebe o caminho de um arquivo com 1 milhão de casas decimais de π, lê o conteúdo e extrai apenas os dígitos após o ponto decimal.
2. O núcleo do algoritmo é um array dp onde dp[i] guarda o maior comprimento de sequência contínua de primos que termina na posição i. Para cada posição, o algoritmo tenta formar um primo com 1 a 4 dígitos e atualiza o array caso encontre um resultado melhor.
3. Ao final, o algoritmo percorre o dp buscando o maior valor. O uso de > em vez de >= garante que, em caso de empate, a sequência que começa mais cedo é escolhida — conforme exigido pelo enunciado. O trecho correspondente é extraído com pi.substring(melhorFim - melhorComp, melhorFim).
4. A função ePrimo rejeita zeros à esquerda e números menores que 2, depois testa divisibilidade até a raiz quadrada do número — otimização clássica que reduz o número de verificações.

# Tecnologias utilizadas

Linguagem: Java

# Execução

Para rodar o programa, basta seguir os seguintes passos:

1. Certifique-se de ter o arquivo `pi-1M.txt` no caminho configurado no código
2. Esteja no diretório onde a classe se encontra
3. Rode no terminal:

```bash
$ java MeuPrograma.java nome_arquivo.txt
```

# Saída

Ao executar o programa você verá a lista de primos que formam a maior sequência encontrada.

Para o arquivo com 1 milhão de casas decimais de π, a saída será:

```text
7016476128273409638979769774019876160194157601529109109253122761838347
```

Que corresponde a uma sequência de **70 dígitos contíguos** (casas decimais 866.347 a 866.416),
particionada em **33 primos** de até 4 algarismos cada.
