# tac

---

## Versão do Python

**Python 3.14.3**

---

## Descrição

O script lê um arquivo grande de forma eficiente, sem carregá-lo inteiramente na memória. Ele divide a leitura em **blocos de 16 MB**, percorrendo o arquivo do fim para o início, e imprime as linhas em ordem inversa.

---

## Como usar

```bash
python main.py <nome_do_arquivo>
```

**Exemplo:**

```bash
python3 main.py 1GB.txt | md5sum
```

---

## Como funciona

1. **Validação de argumentos** — O script verifica se um caminho de arquivo foi fornecido. Caso contrário, exibe uma mensagem de uso correto e encerra.

2. **Leitura em blocos** — O arquivo é lido em blocos de `16 MB` (`16 * 1024 * 1024` bytes) do fim para o início, evitando alto consumo de memória em arquivos grandes.

3. **Controle de posição** — Usando `seek()` e `tell()`, o script navega pelo arquivo de trás para frente, ajustando a posição a cada iteração.

4. **Tratamento de sobras** — Como os blocos podem cortar uma linha ao meio, o início de cada bloco é guardado como `sobra` e concatenado ao bloco seguinte para garantir que nenhuma linha seja perdida ou duplicada.

5. **Inversão das linhas** — As linhas de cada bloco são invertidas com `.reverse()` e escritas na saída padrão via `sys.stdout.buffer`, preservando a codificação binária original.

6. **Tratamento do final do arquivo** — A última linha vazia (resultante de um `\n` final) é removida para evitar uma linha em branco espúria no início da saída.
