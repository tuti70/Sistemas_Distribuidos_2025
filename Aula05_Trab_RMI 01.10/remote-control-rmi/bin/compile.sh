#!/bin/bash

# Criar diretório bin se não existir
mkdir -p bin

# Compilar todos os arquivos
javac -d bin src/shared/*.java src/server/*.java src/client/*.java

echo "Compilação concluída! Arquivos compilados em bin/"