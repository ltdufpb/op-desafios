# Desafio 03

## Informações sobre a implementação

Esta solução lê um número inicial e um número final.

Depois disso, percorre todos os valores dentro desse intervalo e verifica se cada
número continua igual quando seus dígitos são invertidos.

A verificação é feita apenas com operações matemáticas. O programa pega o último
dígito com o resto da divisão por 10, monta o número invertido e remove o último
dígito do número original usando divisão inteira.

Quando isso acontece, o número é considerado palíndromo e é impresso na tela.

## Como rodar o exercício

Entre na pasta da solução:

```bash
cd desafio-03/ju-caju/python
```

Execute o programa:

```bash
python3 resposta.py
```

O programa vai pedir:

- número inicial
- número final

## Como instalar o Python

Se o Python 3 já estiver instalado, você pode confirmar com:

```bash
python3 --version
```

Se não estiver instalado, no Ubuntu ou Debian rode:

```bash
sudo apt update
sudo apt install python3
```
