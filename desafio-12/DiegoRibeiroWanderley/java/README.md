# Potências de 2

O programa recebe um arquivo e a cada linha dele, printa o número da linha, se ele
é sequência de 2, e se for, qual o expoente

# Como funciona

1. Com ajuda da classe BigInteger do Java é possível verificar se o número é maior que 0, com o método `signum()` e se ele possui um único bit 1 com `bitCount()`
2. Se ele estiver a par com as regras, ele é potência de 2
3. Com o método `getLowestSetBit()` conseguimos descobrir a posição desse bit 1, que é justamente o expoente da potência

# Tecnologias utilizadas

Linguagem: Java

# Execução

Para rodar o programa:

1. Certifique-se de estar no mesmo diretório da classe e do arquivo com os números
2. Importante ter apenas um número por linha
3. Rode no terminal:

```bash
$ java MeuPrograma.java nome_arquivo.txt
```

# Saída

Para as seguintes entradas:

```text
4096
281454976710656
18446744073709551616
```
A saída segue o seguinte modelo:

`numero boolean_potencia_de_2 (true se for potência, false se não) expoente`

Teremos as seguintes saídas

```text
4096 true 12
281454976710656 false
18446744073709551616 true 64
