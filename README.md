# Jogo da Forca em LIBRAS
*Protótipo desenvolvido para a matéria de LIBRAS do curso de Ciência da Computação, na Universidade Estadual do Paraná.*

## Info

Este projeto é desenvolvido utilizando a IDE NetBeans, versão 8.2. 

Para a interface é utilizada a biblioteca JavaFX, com FXML para as Views e Java nos controladores. 

## Funcionamento

Ao ser aberto, o jogo carrega uma das palavras contidas na sua base e dispõe as letras em LIBRAS, em ordem aleatória, para que o usuário possa escolher uma de cada vez e dar prosseguimento ao jogo da forca.

Se o usuário cometer 6 erros antes de completar a palavra, o jogo termina.

## Adicionando palavras novas

Para adicionar mais palavras ao jogo, basta adicionar a palavra em uma nova linha no arquivo
```
src > Classes > palavras.txt
```
no formato: *Palavra*=*Dica da palavra*
