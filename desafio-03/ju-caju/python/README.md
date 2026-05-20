# Desafio 03 - Numeros palindromos

Solucao do desafio 03 do grupo [OsProgramadores](https://osprogramadores.com/desafios/).

## Autora

Juliana - [ju-caju](https://github.com/ju-caju)

## Sobre o desafio

O objetivo deste desafio e listar todos os numeros palindromos dentro de um
intervalo informado pelo usuario.

Um numero palindromo e um numero que permanece igual quando seus digitos sao
lidos da direita para a esquerda. Por exemplo, `121`, `1331` e `7` sao
palindromos.

## Como a solucao funciona

A implementacao le dois valores informados pelo usuario: o numero inicial e o
numero final do intervalo.

Em seguida, percorre todos os numeros dentro desse intervalo e verifica se cada
um deles e palindromo usando apenas operacoes matematicas, sem transformar o
numero em texto.

Para fazer essa verificacao, o programa pega o ultimo digito com o resto da
divisao por 10, monta o numero invertido e remove o ultimo digito do numero
original usando divisao inteira.

Ao final, o programa imprime todos os numeros que sao iguais ao seu valor
invertido.

## Requisitos

- Python 3.x
- Nenhuma biblioteca externa e necessaria

## Como executar

Entre na pasta da solucao:

```bash
cd desafio-03/ju-caju/python
```

Execute o programa:

```bash
python3 resposta.py
```

O programa vai pedir:

- numero inicial
- numero final

## Saida esperada

O programa imprime um numero palindromo por linha, considerando o intervalo
informado pelo usuario.

Exemplo usando o intervalo de `1` ate `150`:

```text
1
2
3
4
5
6
7
8
9
11
22
33
44
55
66
77
88
99
101
111
121
131
141
```

## Estrutura dos arquivos

```text
desafio-03/ju-caju/python/
├── README.md
└── resposta.py
```
