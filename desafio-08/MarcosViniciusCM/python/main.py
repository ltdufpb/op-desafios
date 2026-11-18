import math


def main():
  try:
    with open('frac.txt', 'r') as arquivo:
      linhas = arquivo.readlines()
  except FileNotFoundError:
    print("Erro: arquivo 'frac.txt' não encontrado.")
    return

  for linha in linhas:
    linha = linha.strip()
    if not linha:
      continue

    # Se não tem barra, é um número simples
    if '/' not in linha:
      print(linha)
      continue

    partes = linha.split('/')
    if len(partes) != 2:
      print('ERR')
      continue

    try:
      num = int(partes[0])
      den = int(partes[1])
    except ValueError:
      print('ERR')
      continue

    # divisão por 0
    if den == 0:
      print('ERR')
      continue

    inteiro = num // den
    resto = num % den

    # Se a divisão for exata
    if resto == 0:
      print(inteiro)
    else:
      # Simplifica a fração restante
      divisor = math.gcd(resto, den)
      num_simp = resto // divisor
      den_simp = den // divisor

      if inteiro == 0:
        print(f'{num_simp}/{den_simp}')
      else:
        print(f'{inteiro} {num_simp}/{den_simp}')


if __name__ == '__main__':
  main()