# Máquina de Turing

O programa simula a Máquina de Turing, no qual recebe uma fita, nesse caso uma cadeia
de caracteres, e para cada caractere faz uma ação, dependendo do estado da máquina,
começa em 0, e do símbolo em questão. Ao fim da ação se move, podendo ser para a direita,
esquerda ou ficar na mesma posição

O programa recebe como entrada um arquivo, onde cada linha contém um arquivo de regras
e o valor que será trabalhado de acordo com essas regras

# Como funciona

##### Parte 1 - Casamento

1. Primeiro realiza o casamento entre os arquivos e as fitas de entrada
2. Para cada fita e regra realiza as seguintes etapas da parte 2

##### Parte 2 - Regras

1. Para cada linha do arquivo de regras verifica se a linha contém algum ';'
2. Se tiver desconsidera tudo após o ';', já que ele funciona como começo de comentário
3. Vai para a parte 3, o algoritmo da Máquina de Turing

##### Parte 3 - Máquina de Turing

1. Começa o estado em 0 e substitui ' ' (espaço em branco) por '_' na fita
2. Entra no while e repete o looping até o estado atual for uma String que comece com ```halt```
3. Percorre as regras verificando as possíveis regras que o smbolo atual se encaixa
4. Achada todas as regras possíveis e da preferência a aquela mais específica
5. Se não houver nenhuma regra, significa que a entrada deu erro
6. Após isso faz a substituição do símbolo atual pelo novo símbolo
7. Muda o estado conforme a regra
8. Se move para direção apontada pela regra
9. Repete o processo para o próximo símbolo
10. Printa na tela: ```arquivo_de_regras,entrada,saida```

# Tecnologias utilizadas

Linguagem: Java

# Execução

Para rodar o programa, basta seguir os seguintes passos

1. Esteja no diretório onde a classe, o arquivo com os casamentos e os arquivos de regras se encontram
2. Rode no terminal:
```bash
$ java MeuPrograma.java arquivo_casamentos
```

# Saída

Ao executar o programa você verá o arquivo de regras utilizado, a entrada e a saída para cada linha do arquivo de casamentos

Para o arquivo de teste:

```text
pali.tur,1001001
pali.tur,1011100
prime.tur,101
prime.tur,100
prime.tur,102
binary_add.tur,110110 101011
example.tur,A/B/C/D@
```

A saída de cada linha será:

```text
pali.tur,1001001,:)
pali.tur,1011100,:(
prime.tur,101,101 is prime!
prime.tur,100,100 is not prime.
prime.tur,102,ERR
binary_add.tur,110110 101011,1100001
example.tur,A/B/C/D@,AxByCxD.
