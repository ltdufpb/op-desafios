import sys
import os

def main():
    if len(sys.argv) < 2:
        print("Uso correto: python main.py <nome_do_arquivo>")
        sys.exit(1)

    caminho = sys.argv[1]
    tamanho_bloco = 16 * 1024 * 1024

    with open(caminho, 'rb') as f:
        f.seek(0, os.SEEK_END)
        posicao = f.tell()
        sobra = b''
        eh_final_arquivo = True
        
        while posicao > 0:
            tamanho_leitura = min(tamanho_bloco, posicao)
            posicao -= tamanho_leitura
            f.seek(posicao)
            bloco = f.read(tamanho_leitura)
            
            dados = bloco + sobra
            linhas = dados.split(b'\n')
            
            if eh_final_arquivo:
                if linhas and linhas[-1] == b'':
                    linhas.pop()
                eh_final_arquivo = False
                
            if posicao > 0:
                sobra = linhas[0]
                linhas = linhas[1:]
            
            if linhas:
                linhas.reverse()
                sys.stdout.buffer.write(b'\n'.join(linhas) + b'\n')

if __name__ == "__main__":
    main()
