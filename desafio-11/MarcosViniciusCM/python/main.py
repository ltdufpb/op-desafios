import sys


def gerar_primos(limite=9973):
  eh_primo = [True] * (limite + 1)
  eh_primo[0] = eh_primo[1] = False

  for i in range(2, int(limite**0.5) + 1):
    if eh_primo[i]:
      for multiplo in range(i * i, limite + 1, i):
        eh_primo[multiplo] = False

  return {str(num) for num, primo in enumerate(eh_primo) if primo}


def main():
  arq_entrada = sys.argv[1] if len(sys.argv) > 1 else 'pi-1M.txt'

  try:
    with open(arq_entrada, 'r', encoding='utf-8') as f:
      conteudo = f.read().strip()
  except FileNotFoundError:
    print(f"Erro: arquivo '{arq_entrada}' não encontrado.")
    return

  if '.' in conteudo:
    casas = conteudo.split('.')[1]
  else:
    casas = conteudo

  casas = ''.join(casas.split())

  primos = gerar_primos(9973)
  n = len(casas)

  dp = [0] * (n + 1)

  max_tam = 0
  melhor_inicio = 0

  for i in range(n - 1, -1, -1):
    # Dígito '0' nunca inicia um primo válido
    if casas[i] == '0':
      continue

    maior_a_partir_daqui = 0
    for tam in range(1, 5):
      if i + tam <= n and casas[i : i + tam] in primos:
        total = tam + dp[i + tam]
        if total > maior_a_partir_daqui:
          maior_a_partir_daqui = total

    dp[i] = maior_a_partir_daqui

    if dp[i] >= max_tam and dp[i] > 0:
      max_tam = dp[i]
      melhor_inicio = i

  print(casas[melhor_inicio : melhor_inicio + max_tam])


if __name__ == '__main__':
  main()