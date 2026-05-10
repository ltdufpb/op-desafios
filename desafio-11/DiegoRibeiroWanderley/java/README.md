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

##### Parte 1 - Leitura de π

1. Lê o arquivo `pi-1M.txt` e extrai apenas as casas decimais (descarta o `3.` inicial)

##### Parte 2 - Verificação de primo

1. Rejeita imediatamente strings vazias ou com zero à esquerda
2. Testa divisibilidade do número por todos os inteiros de 2 até sua raiz quadrada
3. Se nenhum dividir exatamente, o número é primo

##### Parte 3 - Programação dinâmica

1. Cria o array `dp[]` onde `dp[i]` guarda o início mais recuado de uma sequência que termina na posição `i`
2. Inicializa todas as posições com `∞` (impossível) e `dp[0] = 0` como ponto de partida
3. Para cada posição `i`, testa segmentos de 1 a 4 dígitos terminando ali
4. Se o segmento for primo, avalia duas opções:
    - **Opção A:** esse primo inicia uma sequência nova em `j`
    - **Opção B:** esse primo estende uma sequência que já chegava em `j`, herdando seu início mais recuado
5. Guarda sempre a opção que resulta no início mais recuado (sequência mais longa)
6. Ao fim de cada posição, calcula o comprimento `i - dp[i]` e atualiza o melhor resultado global

##### Parte 4 - Reconstrução da partição

1. Localiza a posição final da melhor sequência (`bestEnd`)
2. Segue os ponteiros `prev[]` de trás para frente até o início da sequência
3. Inverte a lista para obter a ordem correta dos primos
4. Imprime a partição encontrada

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
