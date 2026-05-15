# Big Base: Convertor de Entradas

Este algoritmo recebe como parÂmetros uma base de entrada, uma base de saída e
um valor na base de entrada. O programa consegue transformar o valor fornecido
em um valor na base de saída.

# Como funciona

##### Primeiro ocorrem as verificações para ver se as entradas são validas, as entradas são inválidas se:

1. A base de saída ou a de entrada forem maiores que 62 ou menores que 2
2. O número for negativo
3. O número for muito grande (a base ser maior que 62)
4. Número inválido para a base especificada

##### Após isso o programa realiza os seguintes passos

1. Transforma o valor na base de entrada para um número decimal, multiplicando o valor da casa pelo número da base elevado a posição da casa
2. No passo 1 transforma os caracteres nos seus valores númericos, cada caractere do conjunto ```0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz``` tem um valor, começa em 0 e vai até 61
3. Após isso, leva da base 10 para a base de saída, dividindo o número pela base de saída e guardando os seus restos até o número chegar em 0
4. Já no passo 3, transforma os números nos seus caracteres respectivos, seguindo as mesmas regras do conjunto do passo 2

# Tecnologias utilizadas

Linguagem: Java 8+

# Execução

Para rodar o programa, basta seguir os seguintes passos

1. Esteja no diretório onde a classe se encontra
2. Rode no terminal:
```bash
$ java desafio3.MeuPrograma.java base_entrada base_saida valor_base_entrada
```

# Saída

Ao executar o programa você verá o valor na base de saída

Para as seguintes entradas:

```text
36 16 HELLOWORLD
10 2 32452867
2 10 1234
```
A saída de cada uma será:

```text
647B8839EB1B1
1111011110011000100000011
???