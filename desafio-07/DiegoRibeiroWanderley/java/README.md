# TAC: CAT invertido

O comando funciona de forma inversa ao CAT, ele mostra as linhas do arquivo desejado
em ordem contrária.

# Como funciona

### Parte 1 - Lista de bytes do começo de cada linha

1. Primeiro com o RandomAcessFile é guardado o tamanho de bytes do arquivo
2. Em seguida, ele lê 1MB por vez e faz os seguintes passos
3. Para cada caractere lido verifica se é \n, se não for, incrementa posicaoGlobal em 1
4. Se for, guarda em uma lista ```posicaoGlobal (inicio do bloco) + i (quantidade de caracteres lidos) + 1```

### Parte 2 - Imprimindo ao contrário

1. Começa um laço em ```posicoes.size()```, posicoes é a lista com os bytes do come�o de cada linha, até 0
2. Com o ```raf.seek(posicoes.get(i))``` pula para a linha e lê byte a byte
3. Depois monta a linha como String UFT-8 removendo o \r se necessário
4. Acumula a saída em memória e imprime tudo de uma vez

# Tecnologias utilizadas

Linguagem: Java

# Execução

Para rodar o programa, basta seguir os seguintes passos

1. Esteja no diretório onde a classe se encontra
2. É importante que o arquivo com o texto esteja no mesmo diretório
3. Rode no terminal:
```bash
$ java MeuPrograma.java nome_arquivo.txt
```
# Saída

Ao executar o programa voc� ver� as linhas do texto escritas de forma reversa

Ex.:

java\
python\
c
```java
c
python
java
