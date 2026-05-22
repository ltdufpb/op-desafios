import sys

def calcular_mdc(a, b):
    """Calcula o Máximo Divisor Comum usando o algoritmo de Euclides."""
    while b:
        a, b = b, a % b
    return abs(a)

def simplificar_fracao(linha):
    """Processa uma linha de string e retorna a fração simplificada ou ERR."""
    linha = linha.strip()
    if not linha:
        return ""
    
    # Se não houver barra, é um número inteiro simples
    if '/' not in linha:
        return linha

    try:
        numerador_str, denominador_str = linha.split('/')
        num = int(numerador_str)
        den = int(denominador_str)
    except ValueError:
        return "ERR"

    # Divisão por zero é um erro
    if den == 0:
        return "ERR"

    # Se o numerador for zero, o resultado é simplesmente 0
    if num == 0:
        return "0"

    # Divisão exata (ex: 81/9 -> 9)
    if num % den == 0:
        return str(num // den)

    # Identifica se o resultado final é um número misto (ex: 14/3 -> 4 inteiros e sobram 2)
    parte_inteira = num // den
    resto_numerador = num % den

    # Simplifica a fração restante (ou a fração própria) usando o MDC
    divizor_comum = calcular_mdc(resto_numerador, den)
    num_simplificado = resto_numerador // divizor_comum
    den_simplificado = den // divizor_comum

    # Ajusta o sinal caso o denominador fique negativo na simplificação
    if den_simplificado < 0:
        num_simplificado = -num_simplificado
        den_simplificado = -den_simplificado

    # Se gerou uma parte inteira, formata como número misto (ex: "4 2/3")
    if parte_inteira > 0:
        return f"{parte_inteira} {num_simplificado}/{den_simplificado}"
    else:
        return f"{num_simplificado}/{den_simplificado}"

def main():
    # Verifica se o arquivo foi passado como argumento de linha de comando
    if len(sys.argv) < 2:
        print("Uso: python script.py <caminho_do_arquivo>")
        return

    caminho_arquivo = sys.argv[1]

    try:
        with open(caminho_arquivo, 'r', encoding='utf-8') as arquivo:
            for linha in arquivo:
                resultado = simplificar_fracao(linha)
                if resultado:  # Ignora linhas vazias se houver
                    print(resultado)
    except FileNotFoundError:
        print(f"Erro: O arquivo '{caminho_arquivo}' não foi encontrado.")

if __name__ == "__main__":
    main()
