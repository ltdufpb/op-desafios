# Calculadora de Expressões

Esse programa lê um arquivo contendo expressões matemáticas, uma por linha, e calcula o resultado de cada uma delas, respeitando a precedência de operadores e parênteses.

# Como funciona

1. O programa lê o arquivo fornecido e armazena cada linha como uma expressão a ser calculada
2. Para cada expressão, espaços são removidos e os parênteses são validados antes de qualquer cálculo
3. O cálculo resolve os parênteses de dentro para fora: localiza o último ( aberto e o primeiro ) após ele, resolve a subexpressão entre eles e substitui na expressão original, repetindo até não restar parênteses
4. Sem parênteses, os operadores são resolvidos seguindo a precedência: ^ primeiro, depois * e / da esquerda para a direita, e por último + e - da esquerda para a direita
5. Caso a expressão tenha parênteses mal formados, ou expressão mal formada, imprime ERR SYNTAX
6. Caso haja divisão por zero, imprime ERR DIVBYZERO

# Tecnologias utilizadas

Linguagem: Java

# Execução

1. Esteja no diretório onde a classe e o arquivo com expressões se encontra e execute:

```bash
$ java MeuPrograma.java operacoes.txt
```

O arquivo deve conter uma expressão por linha. Exemplo de arquivo:

```text
4 / (54 - (9 * 6))
54 * * 54 - 1
((79 - 12) * (5 + (2 - 1))
266 + 54 * 4 - ( 41 + 2 ) * 10 / 5 - 7 ^ 3 - 1 + 1 * 0 - (( 45 / 5 * 3 - 1) * 2)
```

# Saída

Para cada expressão do arquivo, o programa imprime o resultado ou uma mensagem de erro.

```text
ERR DIVBYZERO
ERR SYNTAX
ERR SYNTAX
0
