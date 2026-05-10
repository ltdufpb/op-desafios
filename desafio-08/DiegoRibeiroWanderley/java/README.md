# Frações Simples

Este programa recebe como entrada um arquivo com frações simples e retorna o resultado,
que pode ser em forma de fração, se não houver simplificação, em número misto, se possível
e em números naturais, se a divisão for exata

# Como funciona

O programa recebe a String e realiza as seguintes etapas:

1. Verifica se há denominador, se não houver, coloca 1
2. Divide o numerador e denominador num vetor de String
3. Se o denominador for 0, retorna ERR
4. Se o numerador > denominador, retorna um número misto, se a divisão não for exata, ou número natural, se for exata
5. Se numerador < denominador, calcula o MDC
6. Se MDC maior que 1, simplifica a fração e a retorna
7. Se MDC igual a 1, retorna a mesma fração

# Tecnologias utilizadas

Linguagem: Java

# Execução

Para rodar o programa, basta seguir os seguintes passos

1. Esteja no diretório onde a classe se encontra
2. É importante que o arquivo com as frações esteja no mesmo diretório
3. Importante que seja apenas uma fração por linha
4. Rode no terminal:
```bash
$ java MeuPrograma.java nome_arquivo.txt
```

# Saída

Ao executar o programa você verá os resultados das frações

Para o arquivo de teste:

```text
14/3
3/8
4/8
4/3
5
10/0
48/12
```
A saída será:

```java
4 2/3
3/8
1/2
1 1/3
5
ERR
4
