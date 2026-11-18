import sys


def main():
    nome_arq = sys.argv[1] if len(sys.argv) > 1 else 'd12.txt'

    try:
        with open(nome_arq, 'r', encoding='utf-8') as f:
            linhas = f.readlines()
    except FileNotFoundError:
        print(f"Erro: arquivo '{nome_arq}' não encontrado.")
        return

    for linha in linhas:
        texto = linha.strip()
        if not texto:
            continue

        n = int(texto)

        if n > 0 and (n & (n - 1)) == 0:
            expoente = n.bit_length() - 1
            print(f'{n} true {expoente}')
        else:
            print(f'{n} false')


if __name__ == '__main__':
    main()