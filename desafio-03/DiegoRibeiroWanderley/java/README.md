# Palíndromos de números naturais

O seguinte programa é um algoritmo capaz de identificar os palíndromos em um 
intervalo fornecido pelo usuário.

Palíndromos são palavras, nesse caso números, que tem a mesmo forma se invertidos,
nesse caso 101 é palíndromo, já que seu inverso é 101.

# Como funciona

O programa funciona percorrendo o espaço entre os dois números fornecidos, eles
inclusos, e verificando se esse número é palíndromo.

A verificação ocorre da seguinte forma:

1. Cria um objeto StringBuilder
2. Percorre um for começando no tamanho de caracteres do números menos 1 até 0
3. Adiciona no StringBuilder os digitos
4. Verifica se o número, uma String, é igual ao objeto do String Builder
5. Se sim, printa na tela

# Tecnologias utilizadas

Linguagem: Java

# Execução

Para rodar o programa, basta seguir os seguintes passos

1. Esteja no diret�rio onde a classe se encontra
2. Rode no terminal:
```bash
$ java MeuPrograma.java numero_1 numero_2
```
# Saída

Ao executar o programa voc� ver� uma lista dos n�meros pal�ndromos no intervalo escolhido

Ex.: Se o intervalo for de 1000 a 1200 
```java
1001
1111
